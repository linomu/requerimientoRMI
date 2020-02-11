/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteHabitacion.dto;

import ServidorAlertas.dto.ClsClienteDTO;
import java.text.DecimalFormat;

/**
 *
 * @author Lino Alejandro Munoz
 */
public class GestionIndicadores {
    
    public GestionIndicadores() {
        
    }
    
    
    
    public ClsClienteDTO GenerarIndicadores(ClsClienteDTO objCliente) {
        int frecuenciaCardiacaExcedente = 10;
        int sistolicaExcedente = 5;
        int diastolicaExcedente = 10;
        int frecueciaRespiratoriaExcedente = 5;
        float temperaturaExcedente = (float) 0.5;
        int saturacionExcedente = 5;
        
        

        objCliente.setSaturacionOxigeno((float) (Math.random() * (100) + (95 - saturacionExcedente)));
        
        /*Nacimiento - 6 Semanas*/
        if (objCliente.getEdad() > 0 && objCliente.getEdad() <= 0.115068) {
            System.out.println("Generando indicadores para : Nacimiento - 6 Semanas");
            objCliente.setFrecuenciaCardiaca((float) (Math.random() * (140 + frecuenciaCardiacaExcedente) + (120 - frecuenciaCardiacaExcedente)));//Aleatorio entre 160 y 100
            objCliente.setSistolica((float) (Math.random() * (100 + sistolicaExcedente) + (70 - sistolicaExcedente)));
            objCliente.setDiastolica((float) (Math.random() * (68 + diastolicaExcedente) + (50 - diastolicaExcedente)));
            objCliente.setPresionArterial(objCliente.getSistolica() / objCliente.getDiastolica());
            objCliente.setFrecuenciaRespiratoria((float) (Math.random() * (45 + frecueciaRespiratoriaExcedente) + (40 - frecueciaRespiratoriaExcedente)));
            objCliente.setTemperatura( (float) (Math.random() * (38 + temperaturaExcedente) + (38 - temperaturaExcedente)));
            
        }
        if(objCliente.getEdad() > 0.115068 && objCliente.getEdad() <=1){
            System.out.println("Generando indicadores para : 7 Semanas - 1 año");
            objCliente.setFrecuenciaCardiaca ( (float) (Math.random() * (130 + frecuenciaCardiacaExcedente) + (100 - frecuenciaCardiacaExcedente)));//Aleatorio entre 160 y 100
            objCliente.setSistolica((float) (Math.random() * (106 + sistolicaExcedente) + (84 - sistolicaExcedente)));
            objCliente.setDiastolica((float) (Math.random() * (70 + diastolicaExcedente) + (56- diastolicaExcedente)));
            objCliente.setPresionArterial(objCliente.getSistolica() / objCliente.getDiastolica());
            objCliente.setFrecuenciaRespiratoria((float) (Math.random() * (30 + frecueciaRespiratoriaExcedente) + (20 - frecueciaRespiratoriaExcedente)));
            objCliente.setTemperatura( (float) (Math.random() * (37.8 + temperaturaExcedente) + (37.5 - temperaturaExcedente)));
        }
        if(objCliente.getEdad() > 1 && objCliente.getEdad() <=2){
            System.out.println("Generando indicadores para :1 año - 2 años");
            objCliente.setFrecuenciaCardiaca((float) (Math.random() * (120 + frecuenciaCardiacaExcedente) + (100 - frecuenciaCardiacaExcedente)));//Aleatorio entre 160 y 100
            objCliente.setSistolica((float) (Math.random() * (106 + sistolicaExcedente) + (98 - sistolicaExcedente)));
            objCliente.setDiastolica((float) (Math.random() * (70 + diastolicaExcedente) + (58- diastolicaExcedente)));
            objCliente.setPresionArterial(objCliente.getSistolica() / objCliente.getDiastolica());
            objCliente.setFrecuenciaRespiratoria((float) (Math.random() * (30 + frecueciaRespiratoriaExcedente) + (20 - frecueciaRespiratoriaExcedente)));
            objCliente.setTemperatura( (float) (Math.random() * (37.8 + temperaturaExcedente) + (37.5 - temperaturaExcedente)));
        }
        if(objCliente.getEdad() > 2 && objCliente.getEdad() <=6){
            System.out.println("Generando indicadores para : 2 años - 6 años");
            objCliente.setFrecuenciaCardiaca((float) (Math.random() * (120 + frecuenciaCardiacaExcedente) + (80 - frecuenciaCardiacaExcedente)));//Aleatorio entre 160 y 100
            objCliente.setSistolica((float) (Math.random() * (112 + sistolicaExcedente) + (99 - sistolicaExcedente)));
            objCliente.setDiastolica((float) (Math.random() * (70 + diastolicaExcedente) + (64- diastolicaExcedente)));
            objCliente.setPresionArterial(objCliente.getSistolica() / objCliente.getDiastolica());
            objCliente.setFrecuenciaRespiratoria((float) (Math.random() * (30 + frecueciaRespiratoriaExcedente) + (20 - frecueciaRespiratoriaExcedente)));
            objCliente.setTemperatura( (float) (Math.random() * (37.8 + temperaturaExcedente) + (37.5 - temperaturaExcedente)));
        }
        if(objCliente.getEdad() > 6 && objCliente.getEdad() <=13){
            System.out.println("Generando indicadores para : 6 años - 13 años");
            objCliente.setFrecuenciaCardiaca((float) (Math.random() * (100 + frecuenciaCardiacaExcedente) + (80 - frecuenciaCardiacaExcedente)));//Aleatorio entre 160 y 100
            objCliente.setSistolica((float) (Math.random() * (124 + sistolicaExcedente) + (104 - sistolicaExcedente)));
            objCliente.setDiastolica((float) (Math.random() * (86 + diastolicaExcedente) + (64- diastolicaExcedente)));
            objCliente.setPresionArterial(objCliente.getSistolica() / objCliente.getDiastolica());
            objCliente.setFrecuenciaRespiratoria ((float) (Math.random() * (20 + frecueciaRespiratoriaExcedente) + (12 - frecueciaRespiratoriaExcedente)));
            objCliente.setTemperatura( (float) (Math.random() * (37.5 + temperaturaExcedente) + (37 - temperaturaExcedente)));
        }
         if(objCliente.getEdad() > 13 && objCliente.getEdad() <=16){
            System.out.println("Generando indicadores para : 13 años - 16 años");
            objCliente.setFrecuenciaCardiaca((float) (Math.random() * (80 + frecuenciaCardiacaExcedente) + (70 - frecuenciaCardiacaExcedente)));//Aleatorio entre 160 y 100
            objCliente.setSistolica((float) (Math.random() * (132 + sistolicaExcedente) + (118 - sistolicaExcedente)));
            objCliente.setDiastolica((float) (Math.random() * (82 + diastolicaExcedente) + (70- diastolicaExcedente)));
            objCliente.setPresionArterial(objCliente.getSistolica() / objCliente.getDiastolica());
            objCliente.setFrecuenciaRespiratoria((float) (Math.random() * (20 + frecueciaRespiratoriaExcedente) + (12 - frecueciaRespiratoriaExcedente)));
            objCliente.setTemperatura( (float) (Math.random() * (37 + temperaturaExcedente) + (37 - temperaturaExcedente)));
        }
         if(objCliente.getEdad() > 16 ){
            System.out.println("Generando indicadores para :16 años y más");
            objCliente.setFrecuenciaCardiaca((float) (Math.random() * (80 + frecuenciaCardiacaExcedente) + (60 - frecuenciaCardiacaExcedente)));//Aleatorio entre 160 y 100
            objCliente.setSistolica((float) (Math.random() * (140 + sistolicaExcedente) + (110 - sistolicaExcedente)));
            objCliente.setDiastolica((float) (Math.random() * (90 + diastolicaExcedente) + (70- diastolicaExcedente)));
            objCliente.setPresionArterial(objCliente.getSistolica() / objCliente.getDiastolica());
            objCliente.setFrecuenciaRespiratoria((float) (Math.random() * (20 + frecueciaRespiratoriaExcedente) + (12 - frecueciaRespiratoriaExcedente)));
            objCliente.setTemperatura( (float) (Math.random() * (37.2 + temperaturaExcedente) + (36.2 - temperaturaExcedente)));
        }
        
         
        return objCliente;
    }

}
