package com.br.chef.seuchef.client.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GeminiRequest {
    private List<Content> contents;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Content {
        private List<Part> parts;

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        @Setter
        public static class Part {
            private String text;
        }
    }
}
