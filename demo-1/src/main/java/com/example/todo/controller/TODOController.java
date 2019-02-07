package com.example.todo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.entity.TODO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/todolist")
public class TODOController {
	
	@ApiOperation(value = "Muestra la información de una tarea a realizar.", response = TODO.class)
	@ApiResponses(value = {
           @ApiResponse(code = 200, message = "Successfully retrieved TODO"),
           @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
           @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
           @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@RequestMapping(value = "/gettodo/{id}", method = RequestMethod.GET)
	public TODO getTODO(@PathVariable Long id) throws SQLException {
		TODO resultat = null;
		try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:~/todoList", "sa", "");
			String query = "SELECT * FROM TODOS WHERE ID=" + id;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next())
				resultat = new TODO(rs.getLong("ID"), rs.getString("TITLE"), rs.getString("DESC"),
						rs.getString("STATE"), rs.getString("CDATE"), rs.getString("MDATE"));

			stmt.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultat;
	}

	@ApiOperation(value = "Crea una nueva tarea y la guarda a la base de datos.")
	@RequestMapping(value = "/addtodo", method = RequestMethod.POST)
	public ResponseEntity<String> addTODO(@RequestParam(value = "title") String title, @RequestParam(value = "desc") String desc,
			@RequestParam(value = "state") String state) throws SQLException {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date d = new Date();

			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:~/todoList", "sa", "");
			String query = "INSERT INTO TODOS(TITLE, DESC, STATE, CDATE) VALUES('" + title + "','" + desc + "','" + state
					+ "','" + dateFormat.format(d) + "')";
			Statement stmt = conn.createStatement();
			stmt.execute(query);

			stmt.close();
			conn.close();
			return new ResponseEntity<String>("TODO saved successfully", HttpStatus.OK);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("ERROR. TODO was not saved", HttpStatus.NOT_IMPLEMENTED);
		}

	}

	@ApiOperation(value = "Elimina una tarea de la base de datos.")
	@RequestMapping(value = "/deltodo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delTODO(@PathVariable Long id) throws SQLException {
		try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:~/todoList", "sa", "");
			String query = "DELETE FROM TODOS WHERE ID=" + id;
			Statement stmt = conn.createStatement();
			stmt.execute(query);
			stmt.close();
			conn.close();
			return new ResponseEntity<String>("TODO deleted successfully", HttpStatus.OK);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("ERROR. TODO was not deleted", HttpStatus.NOT_IMPLEMENTED);
		}
		
	}
	
	@ApiOperation(value = "Modifica una tarea de la la base de datos.")
	@RequestMapping(value = "/modtodo/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> modTODO(@PathVariable Long id, @RequestParam(value = "title") String title, @RequestParam(value = "desc") String desc,
			@RequestParam(value = "state") String state) throws SQLException {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date d = new Date();

			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:~/todoList", "sa", "");
			String query = "UPDATE TODOS  SET TITLE ='"+title+"', DESC='"+desc+"', STATE='"+state+"', MDATE='"+ dateFormat.format(d) +"' WHERE ID="+id;
			Statement stmt = conn.createStatement();
			stmt.execute(query);

			stmt.close();
			conn.close();
			return new ResponseEntity<String>("TODO updated successfully", HttpStatus.OK);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("ERROR. TODO was not updated", HttpStatus.NOT_IMPLEMENTED);
		}
	}
}
