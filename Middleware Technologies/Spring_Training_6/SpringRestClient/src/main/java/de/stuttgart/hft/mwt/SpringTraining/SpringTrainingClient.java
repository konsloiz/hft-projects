package de.stuttgart.hft.mwt.SpringTraining;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SpringTrainingClient {
    public static void main(String[] args) {
        SpringTrainingClient springClient = new SpringTrainingClient();

        System.out.println("Calling GET for all strings");
        System.out.println("String List= " + springClient.getAllStrings().getBody().toString());

        System.out.println("Add a new string");
        springClient.addNewString();

        System.out.println("String List= " + springClient.getAllStrings().getBody().toString());

        springClient.deleteString();
        System.out.println("String List= " + springClient.getAllStrings().getBody().toString());
    }

    public SpringTrainingClient() {

    }

    private ResponseEntity<String> getAllStrings() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8090/strings";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return response;
    }

    private void addNewString() {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8090/strings/{newString}";

        String newString = "konstantinos";

        HttpEntity<String> request = new HttpEntity<>(newString);

        restTemplate.postForObject(url, request, String.class, newString);

    }

    private void deleteString() {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8090/strings/{stringToDelete}";

        String stringToDelete = "konstantinos";

        restTemplate.delete(url, stringToDelete);
    }

}