package com.quizGpt.formManagement.Common.Controller;

import com.quizGpt.formManagement.Account.Entity.MqResponse;
import com.quizGpt.formManagement.Account.Service.AccountService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

public class MqController {
    private AccountService accountService;

    public MqController(AccountService accountService){
        this.accountService = accountService;
    }
    protected Future<String> GetResponseOrWait(String correlationIdOrUsername) throws TimeoutException {
        int totalWaitTime = 15; // total wait time in seconds
        int waitInterval = 1; // wait interval in seconds
        CompletableFuture<String> futureResponse = new CompletableFuture<>();

        int secondsElapsed = 0; // variable to keep track of elapsed seconds

        while (secondsElapsed < totalWaitTime) {
            //check db
            MqResponse response = accountService.FindMqResponseByCorrelationId(correlationIdOrUsername);
            if(response == null)
                response = accountService.FindFirstByResponseContaining(correlationIdOrUsername);


            if(response != null) {
                String cleanResponse = response.getResponse().replace("response=", "");
                System.out.println("response found : " + cleanResponse );
                futureResponse.complete(cleanResponse);
                accountService.Delete(response);
                return futureResponse;
            }
            // Perform the operation or logic here that you want to repeat every second
            System.out.println("Elapsed time: " + secondsElapsed + " seconds");


            // Sleep for the specified wait interval in milliseconds (1 second = 1000 milliseconds)
            try {
                Thread.sleep(waitInterval * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Increment the seconds elapsed counter
            secondsElapsed += waitInterval;
        }
        throw  new TimeoutException("Could not retreive "+correlationIdOrUsername+" in time");
    }
}
