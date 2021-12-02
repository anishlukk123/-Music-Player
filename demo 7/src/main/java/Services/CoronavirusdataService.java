package Services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import model.locationstats.locationstats;

@Service
public class CoronavirusdataService {
	private String url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
	private ArrayList<locationstats> localstats = new ArrayList<>();
	
	public ArrayList<locationstats> getlocalstats()
	{
		return localstats;
		
	}

	@PostConstruct
	@Scheduled(cron = "0 0 * * * *")
	public void fetchvirusdata() throws IOException, InterruptedException {
		ArrayList<locationstats> newstats = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest Request = HttpRequest.newBuilder().uri(URI.create(url)).build();
		HttpResponse<String> response = client.send(Request, HttpResponse.BodyHandlers.ofString());
		StringReader csvreader = new StringReader(response.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvreader);
		for (CSVRecord record : records) {
			locationstats localstas = new locationstats();
			localstas.setState(record.get("Province/State"));
			localstas.setCountry(record.get("Country/Region"));
			int latesttotal = Integer.parseInt(record.get(record.size() - 1));
			int prevtotal = Integer.parseInt(record.get(record.size() - 2));
			localstas.setLatestotal((latesttotal));
			localstas.setDifffromyesterday((latesttotal-prevtotal));
			newstats.add(localstas);

		}
		this.localstats = newstats;

	}
}
