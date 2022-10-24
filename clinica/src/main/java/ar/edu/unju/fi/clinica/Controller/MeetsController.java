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
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lowagie.text.DocumentException;

import ar.edu.unju.fi.clinica.DTO.DoctorDTO;
import ar.edu.unju.fi.clinica.DTO.PatientDTO;

import ar.edu.unju.fi.clinica.Entity.Meeting;
import ar.edu.unju.fi.clinica.Service.DoctorService;
import ar.edu.unju.fi.clinica.Service.MeetingService;
import ar.edu.unju.fi.clinica.Service.PatientService;
import ar.edu.unju.fi.clinica.View.MeetsExportPDF;

/**
 * @author Diaz, Sebastián Darío
 * Controlador de acciones sobre Meeting en la vista.
 */
@Controller
public class MeetsController {
	@Autowired
	private MeetingService meetingService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private DoctorService doctorService;
	
	/**
	 * Método que lista los Turnos almacenados.
	 * @param model - model: Model
	 * @return La vista general de Turnos.
	 */
	@GetMapping("/meets")
    public String listMeeting(Model model){
        List<Meeting> meets = meetingService.listMeeting();
        model.addAttribute("meets", meets);
        return "meets";
    }
    
	/**
	 * Método para Eliminar un Turno.
	 * @param idMeeting - ID: Long
	 * @return La vista general de Turnos.
	 */
    @GetMapping("/meets/delete/{idMeeting}")
    public String deleteMeeting(@PathVariable("idMeeting")Long idMeeting){
        meetingService.deleteMeetingById(idMeeting);
        return "redirect:/meets";
    }
    
    /**
     * Método para exportar el Listado de Turnos en formato PDF.
     * @param response
     * @throws DocumentException
     * @throws IOException
     */
    @GetMapping("meets/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
    	response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Listado_Usuarios-" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        
        List<Meeting> listMeets = meetingService.listMeetingAsc();
        MeetsExportPDF exporter = new MeetsExportPDF(listMeets);
        exporter.export(response);     
    }
    
    /**
     * Método que muestra el formulario para agregar un Turno.
     * @param model - model: Model
     * @return La vista de formulario para agregar un Turno.
     */
    @GetMapping("/meets/add")
    public String showFormAddMeet(Model model) {
    		Meeting meeting = new Meeting();
        	List<PatientDTO> listPatient = patientService.listPatient();
        	List<DoctorDTO> listDoctor = doctorService.listDoctor();

        	model.addAttribute("meet", meeting);
        	model.addAttribute("patients", listPatient);
        	model.addAttribute("doctors", listDoctor);
        	
    	return "frmAddMeet";
    }
    
    /**
     * Método POST que almacena un nuevo Turno.
     * @param meeting
     * @param result
     * @param model
     * @return La vista general de Turnos.
     */
    @PostMapping("/meets/add/save")
    public String addMeet(@Valid @ModelAttribute("meet") Meeting meeting,   BindingResult result, Model model) {
    	//@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDateTime horaMeet
    	
    	if(result.hasErrors()){
            model.addAttribute("meet", meeting);
            return "/meets/add";
        }
    	meeting.setStartMeeting(meeting.getStartMeeting());
    	meeting.setEndMeeting(meeting.getStartMeeting().plusMinutes(30));
    	
    	meeting.setStatus(true);
    	meetingService.addMeetingEntity(meeting);
    	return "redirect:/meets";
    }
}
