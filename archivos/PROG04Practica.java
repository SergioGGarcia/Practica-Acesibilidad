/*
 * STAR WARS CODIGOS SECRETOS
 */


package prog04practica;

/**
 *
 * @author: Sergio García García
 */

import java.util.Scanner;

public class PROG04Practica {


    public static void main(String[] args) {
        
        
        // INICIO
        
        System.out.println ("=== STAR WARS CÓDIGOS SECRETOS ===\n" +
        "Hace mucho tiempo, en una galaxia muy, muy lejana... La Princesa\n" +
        "Leia, Luke Skywalker, Han Solo, Chewbacca, C3PO y R2D2 viajan en\n" +
        "una nave imperial robada en una misión secreta para infiltrarse en otra\n" +
        "estrella de la muerte que el imperio está construyendo para destruirla.\n" +
        "(Presiona Intro para continuar)");
        
        Scanner sc = new Scanner (System.in);
        System.out.println (sc.nextLine());    // Acción para avanzar nivel pulsando intro
        
        
        // NIVEL 1
        
        int S1 = (int) (1 + Math.random() * (10+1));      // Número aleatorio entre 1 y 10
        int S2 = (int) (20 + Math.random() * (30-20+1));  // Número aleatorio entre 20 y 30
        int respuesta1 = 0;
        int respuestaU1;
        
        System.out.println ("Los problemas empiezan cuando deben realizar un salto hiperespacial\n" +
        "hasta al sistema " +  S1 + " en el sector " + S2 + ", pero el sistema de navegación está\n" +
        "estropeado y el computador tiene problemas para calcular parte de las\n" +
        "coordenadas de salto.\n" +
        "Chewbacca, piloto experto, se da cuenta que falta el cuarto número de\n" +
        "la serie.\n" +
        "Recuerda de sus tiempos en la academia de pilotos que para calcularlo\n" +
        "hay que calcular el sumatorio entre el nº del sistema y el nº del sector\n" +
        "(ambos inclusive).");
        
        
        do {
            respuesta1 = respuesta1 + S1;       // Bucle do while para realizar
            ++S1;                               // el sumatorio
        } while (S1 <= S2);
        
        
        
        System.out.println ("¿Qué debe introducir?");
        respuestaU1 = sc.nextInt();         // Acción para leer la respuesta del usuario                    
        
        
        if (respuestaU1 == respuesta1) {    // Comparar la respuesta correcta con 
                                            // la del usuario para ver si pasa de nivel o pierde
            
            // NIVEL 2
            
            
            int P1 = (int) (1 + Math.random() * (7+1));     // Número entero aleatorio entre 1 y 7.
            int P2 = (int) (8 + Math.random() * (12-8+1));  // Número entero aleatorio entre 8 y 12.
            int respuesta2 = 1;
            int respuestaU2;
            
            System.out.println ("Gracias a Chewbacca consiguen llegar al sistema correcto y ven a lo\n" +
            "lejos la estrella de la muerte. Como van en una nave imperial robada se\n" +
            "aproximan lentamente con la intención de pasar desapercibidos. De\n" +
            "repente suena el comunicador. “Aquí agente de espaciopuerto " + P1 + "\n" +
            "contactando con nave imperial " + P2 + ". No están destinados en este sector.\n" +
            "¿Qué hacen aquí?”. Han Solo coge el comunicador e improvisa.\n" +
            "“Eh... tenemos un fallo en el... eh... condensador de fluzo... Solicitamos\n" +
            "permiso para atracar y reparar la nave”. El agente, que no se anda con\n" +
            "tonterías, responde “Proporcione código de acceso o abriremos fuego”.\n" +
            "Han Solo ojea rápidamente el manual del piloto que estaba en la\n" +
            "guantera y da con la página correcta. El código es el productorio entre\n" +
            "el nº del agente y el nº de la nave (ambos inclusive).");
            
            do {
                respuesta2 = respuesta2 * P1;       // Bucle do while como el de 
                ++P1;                               // antes pero en vez de sumar multiplicar
            } while (P1 <= P2);
            
            
            
            System.out.println ("¿Cúal es el código");
            respuestaU2 = sc.nextInt();     //Ación para leer respuesta del usuario
            
            
            if (respuestaU2 == respuesta2) {    // Comparar la respuesta correcta con 
                                                // la del usuario para ver si pasa de nivel o pierde
                
                // NIVEL 3
                
                int N = (int) (50 + Math.random() * (100-50+1));  // Número entero aleatorio entre 50 y 100.
                int respuesta3 = 1;
                int cociente;       // Cociente de la divisiòn de N entre 10
                int fact = 1;
                
                
                System.out.println ("Han Solo proporciona el código correcto. Atracan en la estrella de la\n" +
                "muerte, se equipan con trajes de soldados imperiales que encuentran\n" +
                "en la nave para pasar desapercibidos y bajan. Ahora deben averiguar\n" +
                "en qué nivel de los " + N + " existentes se encuentra el reactor principal. Se\n" +
                "dirigen al primer panel computerizado que encuentran y la Princesa\n" +
                "Leia intenta acceder a los planos de la nave pero necesita introducir\n" +
                "una clave de acceso. Entonces recuerda la información que le\n" +
                "proporcionó Lando Calrissian “La clave de acceso a los planos de la\n" +
                "nave es el factorial de N/10 (redondeando N hacia abajo), donde N es\n" +
                "el nº de niveles”.");
                
                cociente = N / 10;          // División que pide enunciado
                Math.round(cociente);     // Redondeo al siguiente nùmero
                
                
                while (cociente > 1) {
                    fact = fact * cociente;     // Bucle while para calcular factorial
                    --cociente;
                }
                
                System.out.println ("¿Cual es el nivel correcto?");
                respuesta3 = sc.nextInt();      // Acción para leer la respuesta del usuario
                
               
                if (respuesta3 == fact) {   // Comparar el factorial correcto con la respuesta
                                            // del usuario para ver si pasa de nivel o pierde
                    
                    // NIVEL 4
                    
                    int P = (int) (10 + Math.random() * (100-10+1));   // Número entero aleatorio entre 10 y 100.
                    int respuesta4 = 1;
                    int respuesta4U;
                    int divisor = 2;    // Número para dividir P para
                                        // comprobar si es primo o no
                    
                    
                    
                    System.out.println ("Gracias a la inteligencia de Leia llegan al nivel correcto y encuentran la\n" +
                    "puerta acorazada que da al reactor principal. R2D2 se conecta al panel\n" +
                    "de acceso para intentar hackear el sistema y abrir la puerta. Para\n" +
                    "desencriptar la clave necesita verificar si el número " + P + " es primo o no. Si\n" +
                    "es primo introduce un 1, si no lo es introduce un 0.");
                    
                    while (divisor <= 5) {      // Bucle while para calcular si es primo
                       
                        if (P % divisor == 0) {     // Comprobación si el resto de P entre
                            respuesta4 = 0;         // el divisor es 0, lo que quiere decir que 
                        }                           // no es primo
                        divisor++;
                    }
                    
                    
                    respuesta4U = sc.nextInt();     // Leer respuesta de usuario
                    
                    
                    if (respuesta4U == respuesta4) {    // Comparar la respuesta correcta con 
                                                        // la del usuario para ver si pasa de nivel o pierde
                        
                        
                        // NIVEL 5
                        
                        int M = (int) (5 + Math.random() * (10-5+1));  // Número entero aleatorio entre 50 y 100.
                        int S = (int) (5 + Math.random() * (10-5+1));  // Número entero aleatorio entre 50 y 100.
                        int respuesta5;
                        int factM = 1;
                        int factS = 1;
                        
                        System.out.println ("Consiguen entrar al reactor. Ya solo queda que Luke Skywalker coloque\n" +
                        "la bomba, programe el temporizador y salir de allí corriendo. Necesita\n" +
                        "programarlo para que explote en exactamente " + M + " minutos y " + S + " segundos,\n" +
                        "el tiempo suficiente para escapar antes de que explote pero sin que el\n" +
                        "sistema de seguridad anti-explosivos detecte y\n" +
                        "desactive la bomba. Pero el temporizador utiliza un reloj Zordgiano un\n" +
                        "tanto peculiar. Para convertir los minutos y segundos al sistema\n" +
                        "Zordgiano hay que sumar el factorial de " + M + " y el factorial de "+ S);
                        
                        
                        while (M > 1) {
                            factM = factM * M;      // Bucle while igual que en el nivel 3
                            --M;                    // para calcular factorial de los minutos
                        }
                        
                        while (S > 1) {
                            factS = factS * S;      // Bucle while igual que el anterior para
                            --S;                    // calcular factorial de los segundos
                        }
                        
                        
                        System.out.println ("¿Qué valor debe introducir?");
                        respuesta5 = sc.nextInt();      // Leer respuesta del usuario
                        
                        
                        if (respuesta5 == factM + factS) {  // Comparar la suma de los factoriales con la 
                                                            // respuesta del usuario para ver si gana o pierde
                            
                            // ETAPA GANAR
                            
                            System.out.println("Luke Skywalker introduce el tiempo correcto, activa el temporizador y\n" +
                            "empiezan a sonar las alarmas. Salen de allí corriendo, no hay tiempo\n" +
                            "que perder. La nave se convierte en un hervidero de soldados de arriba\n" +
                            "a abajo y entre el caos que les rodea consiguen llegar a la nave y salir\n" +
                            "de allí a toda prisa. A medida que se alejan observan por la ventana la\n" +
                            "imagen de la colosal estrella de la muerte explotando en\n" +
                            "el silencio del espacio, desapareciendo para siempre junto a los restos\n" +
                            "del malvado imperio.\n" +
                            "¡Has salvado la galaxia gracias a la Fuerza Jedi de las matemáticas!\n" +
                            "Enhorabuena ;D");
                            
                            
                            
                            
                        
                        } else {            // ETAPA PERDER
                            System.out.println ("Ese no era el código correcto... La misión ha sido un fracaso... :( :( :(\n" +
                            "Todavía no eres un Maestro Jedi de las Matemáticas. ¡Vuelve a\n" +
                            "intentarlo!");
                        }
                        
                    } else {            // ETAPA PERDER
                        System.out.println ("Ese no era el código correcto... La misión ha sido un fracaso... :( :( :(\n" +
                            "Todavía no eres un Maestro Jedi de las Matemáticas. ¡Vuelve a\n" +
                            "intentarlo!");
                    }
                    
                    
                } else {            // ETAPA PERDER
                    System.out.println ("Ese no era el código correcto... La misión ha sido un fracaso... :( :( :(\n" +
                            "Todavía no eres un Maestro Jedi de las Matemáticas. ¡Vuelve a\n" +
                            "intentarlo!");
                }
                
                
            } else {            // ETAPA PERDER
                System.out.println ("Ese no era el código correcto... La misión ha sido un fracaso... :( :( :(\n" +
                            "Todavía no eres un Maestro Jedi de las Matemáticas. ¡Vuelve a\n" +
                            "intentarlo!");
            }
            
            
        } else {            // ETAPA PERDER
            System.out.println ("Ese no era el código correcto... La misión ha sido un fracaso... :( :( :(\n" +
                            "Todavía no eres un Maestro Jedi de las Matemáticas. ¡Vuelve a\n" +
                            "intentarlo!");
        }
        
        System.out.println ("Gracias por jugar :D");    // ETAPA FINAL
    }
    
}