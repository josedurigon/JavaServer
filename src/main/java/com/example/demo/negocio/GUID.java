package com.example.demo.negocio;

import java.util.UUID;

public class GUID {
    public static UUID generateUID(){
        return java.util.UUID.randomUUID();
    }
}
