/**
 * 
 */
package ar.edu.unju.fi.clinica.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lowagie.text.DocumentException;

import ar.edu.unju.fi.clinica.DTO.UserDTO;
import ar.edu.unju.fi.clinica.Service.UserService;
import ar.edu.unju.fi.clinica.View.UserExportPDF;

/**
 * @author Diaz, Sebastián Darío
 *
 */
@Controller
public class UsersController {

	@Autowired
	private UserService userService;
	
	//list of users
    @GetMapping("/users")
    public String users(Model model){
        List<UserDTO> users = userService.listUser();
        model.addAttribute("users", users);
        return "users";
    }
    
    @GetMapping("users/delete/{idPerson}")
	public String deleteUser(@PathVariable("idPerson")Long idPerson) {
		userService.deleteUserByID(idPerson);
		return "redirect:/users";
	}
    
    @GetMapping("/users/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Listado_Usuarios-" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<UserDTO> listUsers = userService.listUsersAsc();
         
        UserExportPDF  exporter = new UserExportPDF(listUsers);
        exporter.export(response);     
    }
}
