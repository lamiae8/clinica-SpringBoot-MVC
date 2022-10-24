/**
 * 
 */
package ar.edu.unju.fi.clinica.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.clinica.DTO.UserDTO;
import ar.edu.unju.fi.clinica.Entity.User;
import ar.edu.unju.fi.clinica.Service.UserService;

/**
 * @author Diaz, Sebastián Darío
 * Controlador de Registro de User en la vista.
 */
@Controller
public class AuthController {

	@Autowired
	private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Carga la vista principal.
     * @return Página prinpical index.html
     */
    @GetMapping("/index")
    public String showHome(){
        return "index";
    }

    /**
     * Carga formulario de Login.
     * @return Vista formulurio login.html
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * Carga formulario de registro.
     * @param model - modelo: Model
     * @return Vista register.html
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register";
    }

    /**
     * Método POST, Añadir usuario.
     * @param userDto - usuario: UserDTO.
     * @param result 
     * @param model
     * @return Formulario con mensaje de éxito.
     */
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDTO userDto, BindingResult result, Model model){

    	User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null, "El E-Mail ingresado ya se encuentra registrado en el sistema.");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.addUser(userDto);
        return "redirect:/register?success";
    }
    
    /**
     * Configura la vista por defecto, según el rol.
     * @param request - peticion http: HttpServletRequest 
     * @return String que contiene la url correspondiente.
     */
    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
    	String urlDefault="";
        if (request.isUserInRole("ROLE_ADMINISTRATOR")) {
        	urlDefault = "redirect:/users";
        }
        if (request.isUserInRole("ROLE_OPERATOR")) {
        	urlDefault = "redirect:/meets";
        }
       return urlDefault;
    }
    
}
