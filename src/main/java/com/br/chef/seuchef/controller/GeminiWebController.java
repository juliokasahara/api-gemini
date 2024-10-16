package com.br.chef.seuchef.controller;

import com.br.chef.seuchef.client.GeminiFeignClient;
import com.br.chef.seuchef.client.request.ClientRequest;
import com.br.chef.seuchef.client.request.GeminiRequest;
import com.br.chef.seuchef.client.response.GeminiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class GeminiWebController {
    @Value("${gemini.key}")
    private String key;
    @Autowired
    private GeminiFeignClient client;
    @GetMapping("/")
    public String showConsultaForm(Model model) {
        model.addAttribute("geminiRequest", new ClientRequest());
        return "consulta";
    }
    @PostMapping("/consulta")
    public ModelAndView processConsulta(@ModelAttribute @Valid ClientRequest clientRequest) {
        ModelAndView modelAndView = new ModelAndView("result");
        String respostaGemini = chamarApiGemini(clientRequest);

        if (respostaGemini == null || respostaGemini.isEmpty()) {
            modelAndView.addObject("result", "Erro ao consultar API do Gemini.");
        } else {
            modelAndView.addObject("result", respostaGemini);
        }

        return modelAndView;
    }
    private String chamarApiGemini(ClientRequest request) {
        GeminiRequest.Content.Part part = new GeminiRequest.Content.Part("Gostaria de aprender a fazer " + request.getQuery());
        GeminiRequest.Content content = new GeminiRequest.Content(Arrays.asList(part));
        GeminiRequest geminiRequest = new GeminiRequest(Arrays.asList(content));

        ResponseEntity<GeminiResponse> response = client.generateContent(key,geminiRequest);
        if(response.getStatusCode().value() != 200){
            return null;
        }
        return response.getBody().getCandidates().get(0).getContent().getParts().get(0).getText().replace("/n","<br/>");
    }
}
