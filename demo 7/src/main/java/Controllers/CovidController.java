package Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Services.CoronavirusdataService;
import model.locationstats.locationstats;

@Controller

public class CovidController {
	@Autowired
	CoronavirusdataService Cornoavirusdataservice = new CoronavirusdataService();

	@GetMapping(value = "")
	public String home(Model model) {
		model.addAttribute("locationstats", Cornoavirusdataservice.getlocalstats());
	List<locationstats>localstats = Cornoavirusdataservice.getlocalstats();
int totalcases = localstats.stream().mapToInt(stat->stat.getLatestotal()).sum();
int previoustotalcases = localstats.stream().mapToInt(stat->stat.getDifffromyesterday()).sum();
System.out.print(totalcases);

model.addAttribute("locationstats", localstats);
model.addAttribute( "totalcases", totalcases);
model.addAttribute("previoustotalcases", previoustotalcases);
		return "home";
	}

}