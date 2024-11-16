/*
 cSpell:ignore dotenv 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.casinoRoyal.config.env;

import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author tutaa
 */
public class EnvArchivo {
    public static Dotenv dotenv = Dotenv.configure()
            .directory("src/Code/env")
            .load();

}
