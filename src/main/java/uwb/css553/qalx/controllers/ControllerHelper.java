package uwb.css553.qalx.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import uwb.css553.qalx.models.PillBoxRecord;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ControllerHelper {

        private static final Logger log = LoggerFactory.getLogger(ControllerHelper.class);

        public static void getAllByPid(Integer pid) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/qalx")
                    .queryParam("pid", pid);

            HttpEntity<?> entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<PillBoxRecord[]> response = restTemplate.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    PillBoxRecord[].class);

            PillBoxRecord[] array = response.getBody();
            for(int i = 0; i < array.length; i++) {
                System.out.println(array[i].toString());
            }
        }
        public static void getAllByPsid(Integer psid) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/qalx")
                    .queryParam("psid", psid);

            HttpEntity<?> entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<PillBoxRecord[]> response = restTemplate.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    PillBoxRecord[].class);

            PillBoxRecord[] array = response.getBody();
            for(int i = 0; i < array.length; i++) {
                System.out.println(array[i].toString());
            }
        }
        public static void getAllByDate(Date startDate, Date endDate) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/qalx")
                    .queryParam("startDate", startDate)
                    .queryParam("endDate", endDate);

            HttpEntity<?> entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<PillBoxRecord[]> response = restTemplate.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    PillBoxRecord[].class);

            PillBoxRecord[] array = response.getBody();
            for(int i = 0; i < array.length; i++) {
                System.out.println(array[i].toString());
            }
        }
        public static void getAllByPidAndDate(Integer pid, Date startDate, Date endDate) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/qalx")
                    .queryParam("pid", pid)
                    .queryParam("startDate", startDate)
                    .queryParam("endDate", endDate);

            HttpEntity<?> entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<PillBoxRecord[]> response = restTemplate.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    PillBoxRecord[].class);

            PillBoxRecord[] array = response.getBody();
            for(int i = 0; i < array.length; i++) {
                System.out.println(array[i].toString());

                SimpleDateFormat dateDate = new SimpleDateFormat("yyyy/MM/dd");
                SimpleDateFormat dateTime = new SimpleDateFormat("HH:mm:ss");
                String dateDateString = dateDate.format(array[i].getdate()); // this String is your Date
                String dateTimeString = dateTime.format(array[i].getdate()); // this string is your Time;
                System.out.println(dateDateString);
                System.out.println(dateTimeString);
                //        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //        return df.format(date2);

            }
        }
        public static void getAll() {
            RestTemplate restTemplate = new RestTemplate();
            PillBoxRecord[] quote = restTemplate.getForObject("http://localhost:8080/qalx", PillBoxRecord[].class);
            int size = quote.length;

            for(int i = 0; i<size; i++) {
                System.out.println(quote[i].toString());
            }
        }
        // define what to put in db...
        public static void putInDB() {

            // patient 1 - Taken 10 mins late - irregularity, no alert
            // jan 1 2018 00:00:00
            Date date1 = new Date(118,0,1,0,0,0);
            postEntry(1,date1,2,0);

            Date date2 = new Date(118, 0, 1, 0, 5, 0);
            postEntry(1,date2,2,0);

            Date date3 = new Date(118, 0, 1, 0, 10, 0);
            postEntry(1,date3,2,1);

            // patient 2 - taken within 5 mins - green
            Date date4 = new Date(118, 0, 1, 0, 0, 0);
            postEntry(2,date4,1,0);

            Date date5 = new Date(118, 0, 1, 0, 5, 0);
            postEntry(2,date5,1,1);

            // patient 2 - not taken after 15 mins - alert
            Date date7 = new Date(118, 0, 1, 0, 0, 0);
            postEntry(3,date7,1,0);

            Date date8 = new Date(118, 0, 1, 0, 5, 0);
            postEntry(3,date8,1,0);

            Date date9 = new Date(118, 0, 1, 0, 10, 0);
            postEntry(3,date9,1,0);

            Date date10 = new Date(118, 0, 1, 0, 15, 0);
            postEntry(3,date10,1,0);


        }
        public static void postEntry(
                Integer pid,
                @DateTimeFormat(pattern = "EEE MMM dd HH:mm:ss z yyyy") Date date,
                //Integer pillMissed,
                Integer psid,
                Integer status) {
            RestTemplate restTemplate = new RestTemplate();

            LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/rest");

            builder.queryParam("pid", pid);
            builder.queryParam("date", date);
            //builder.queryParam("pillMissed", 0);
            builder.queryParam("psid", psid);
            builder.queryParam("status", status);

            String result = "";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, requestEntity, String.class);

            HttpStatus statusCode = responseEntity.getStatusCode();
            if (statusCode == HttpStatus.ACCEPTED) {
                result = responseEntity.getBody();
            }
            log.info(result);
        }

}