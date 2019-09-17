package Prova.Prova;

import java.util.Random;
import java.util.StringTokenizer;  //Bisogna importare la classe

import org.apache.poi.ss.formula.functions.Count;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.impl.instance.TimeDateImpl;
import org.camunda.bpm.model.bpmn.impl.instance.TimerEventDefinitionImpl;
import org.camunda.bpm.model.bpmn.instance.BoundaryEvent;
import org.camunda.bpm.model.bpmn.instance.EndEvent;
import org.camunda.bpm.model.bpmn.instance.EventBasedGateway;
import org.camunda.bpm.model.bpmn.instance.ExclusiveGateway;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.IntermediateCatchEvent;
import org.camunda.bpm.model.bpmn.instance.MessageEventDefinition;
import org.camunda.bpm.model.bpmn.instance.Participant;
import org.camunda.bpm.model.bpmn.instance.SendTask;
import org.camunda.bpm.model.bpmn.instance.SignalEventDefinition;
import org.camunda.bpm.model.bpmn.instance.StartEvent;
import org.camunda.bpm.model.bpmn.instance.Task;
import org.camunda.bpm.model.bpmn.instance.TimeDuration;
import org.camunda.bpm.model.bpmn.instance.TimerEventDefinition;


import java.io.*;
import java.lang.*;

public class FromTextToBPMN{ 
    
	static int delta = 0;
		
	public static void main(String [] args) throws IOException{
		
		
		HelpClass helpClass = new HelpClass();
		helpClass.create();
		Random random = new Random();
		
				String input = "The process CSR is a pool." +
						"The process Customer is a pool."  +
						"The process Admin is a black pool." + 
						"The process CSR starts by propose mortgage offer before perform a send message send offer." + 
						"The process CSR performs a send message mortgage offer by send offer after the start and before wait for a reply." + 
						"The process CSR expects a reply after perform a send message mortgage offer and before receive a message reject offer and before receive a message accept offer." + 
						"The process Customer receives a message mortgage offer and starts by offer received before the decision." + 
						"The process Customer must take a decision (Offer accepted?) after the start and before perform a send message offer rejected and before perform a send message offer accepted." + 
						"The decision is: “Offer accepted?”." + 
						"In the first case, the process Customer performs a send message offer rejected by reject offer after the decision and before the end." + 
						"The process Customer ends by offer rejected after perform a send message offer rejected." + 
						"In the second case, the process Customer performs a send message offer accepted by accept offer after the decision and before wait for a reply." + 
						"The process Customer expects a reply after perform a send message offer accepted and before receive a message requesting docs and before receive a message ok offer." + 
						"The process CSR receives a message offer rejected by reject offer after wait for a reply and before perform the activity update details." + 
						"The process CSR performs the activity by update details after receive a message offer rejected and before perform the activity work archived." + 
						"The process CSR performs the activity by work archived after the activity update details and before the activity order cancelled." + 
						"The process CSR performs the activity by order cancelled after the activity work archived and before the end." + 
						"The process CSR ends by order cancelled after the activity order cancelled." + 
						"The process CSR receives a message offer accepted by accept offer after wait for a reply and before perform the activity check documents." + 
						"The process CSR performs the activity by check documents after receive a message offer accepted and before the decision." + 
						"The process CSR must take a decision (Documents attached?) after the activity check documents and before perform a send message requesting docs and before perform a send message move." + 
						"The decision is: “Documents attached?”." + 
						"In the first case, the process CSR performs a send message move by inistration after the decision and before perform a send message ok offer." + 
						"The process Admin catches the message move."+
						"The process CSR performs a send message ok order by ok offer after perform a send message move and before the end." + 
						"The process CSR ends by offer sold after perform a send message ok offer." + 
						"The process Customer receives a message ok offer by ok offer after wait for a reply and before the end." + 
						"The process Customer ends by offer accepted after receive a message ok offer." + 
						"In the second case, the process CSR performs a send message requesting docs by requesting outstanding documents after the decision end before receive a message documents." + 
						"The process Customer receives a message requesting docs by outstanding documents requested after wait for a reply and before perform a send message documents." + 
						"The process Customer performs a send message documents by send documents after receive a message requesting docs and before end." + 
						"The process Customer ends by documents sent after perform a send message documents." + 
						"The process CSR receives a message documents by documents received after perform a send message requesting docs and before wait." + 
						"The process CSR waits by 2 weeks after receive a message documents and before a decision." + 
						"The process CSR must take a decision (Documents received?) after wait and before perform a send message move and before the activity update details." + 
						"In the first case, the process CSR performs a send message move by move to administration after the decision and before the end." +
						"The process Admin catches the message move." +
						"The process CSR ends by offer sold after perform a send message move." + 
						"In the second case, the process CSR performs the activity by update details after the decision and before the activity archive." + 
						"The process CSR performs the activity by archive after the activity update details and before the activity cancellation." + 
						"The process CSR performs the activity by cancellation after the activity archive and before the end." + 
						"The process CSR ends by offer cancelled after the activity cancellation." + "The process PIPPO performs a receive message ID MESSAGGIO by LABEL MESSAGGIO after AFTERQUALCOSA and before BEFOREQUALCOS'ALTRO." +
						"The process PIPPO sends a message ID MESSAGGIO and ends by LABEL MESSAGGIO after AFTERQUALCOSA and before BEFOREQUALCOS'ALTRO." + "The process PIPPO customer sends a message ID MESSAGGIO by LABEL MESSAGGIO after AFTERQUALCOSA and before BEFOREQUALCOS'ALTRO." + 
						"The choice is: “Which kind of goods?”." + 
						"The process PIPPO begins at LABEL TIMER before BEFORE QUALCOSA." +
						"The process PIPPO begins in LABEL TIMER before BEFORE QUALCOSA." +
						"The process PIPPO begins every LABEL TIMER before BEFORE QUALCOSA." +
						"The process PIPPO waits by LABEL START-TIMER after AFTER QUALCOSA and before BEFORE QUALCOS'ALTRO."+
						"SCRIVI QUALCOSA DI SBAGLIATO.";
	
				String input2 = "The process Customer is a pool." + 
						"The process Online Service is a pool." + 
						"The process Customer starts by access eCommerce before a decision." + 
						"The process Customer must take a decision after the start and before the activity create profile and before the activity change profile." + 
						"The decision is: “User registered?”." + 
						"In the first case the process Customer performs the activity by create profile after the decision and before complete the decision." + 
						"In the second case the process Customer performs the activity by change profile after the decision and before complete the decision." + 
						"The process Customer completes the decision after the activity create profile and after the activity change profile and before the activity choose goods." + 
						"The process Customer performs the activity by choose goods after complete the decision by ciao and before make a choice." + 
						"The process Customer must make a choice after the activity choose goods and before the activity choose cold goods and before the activity choose warm goods." + 
						"The choice is: “Which kind of goods?”." + 
						"In the first case the process Customer performs the activity by choose cold goods after make a choice and before complete the choice." + 
						"In the second case the process Customer performs the activity by choose warm goods after make a choice and before complete the choice." + 
						"The process Customer completes the choice after the activity choose cold goods and after the activity choose warm goods and before the activity select delivery time." + 
						"The process Customer performs the activity by select delivery time after complete the choice and before the activity select credit card number." + 
						"The process Customer performs the activity by select credit card number after the activity select delivery time and before send a message order selected." + 
						"The process Customer sends a message order selected by send order after the activity select credit card number and before receive a message confirmation." + 
						"The process Online Service receives a message order selected and starts by receive order before the activity check goods order." + 
						"The process Online Service performs the activity by check goods order after the start and before make a choice." + 
						"The process Online Service must make a choice after the activity check goods order and before the activity prepare goods and before the activity take goods from the storehouse." + 
						"The choice is: “Which kind of goods?”." + 
						"In the first case the process Online Service performs the activity by prepare goods after make a choice and before wait." + 
						"The process Online Service waits by 30 minutes before delivery after the activity prepare goods and before complete the choice." + 
						"In the second case the process Online Service performs the activity by take goods from the storehouse after make a choice and before complete the choice." + 
						"The process Online Service completes the choice after wait and after the activity take goods from storehouse." + 
						"The process Online Service performs the activity by save the order after complete the choice and before send a message confirmation." + 
						"The process Online Service sends a message confirmation by send confirmation after the activity save the order and before the activity check open orders." + 
						"The process Customer receives a message confirmation by receive confirmation after send a message order selected and before take a decision." + 
						"The process Customer must take a decision after receive a message confirmation and before wait and before wait." + 
						"The decision is: “Cancel?”." + 
						"In the first case the process Customer waits by until 1h before delivery after decision and before the activity cancel order." + 
						"The process Customer performs the activity by cancel order after wait and before end." + 
						"The process Customer ends by order cancelled after the activity cancel order." + 
						"In the second case the process Customer waits by wait for the order after the decision and before receive a message order." + 
						"The process Online Service performs the activity by check open orders after send a message confirmation and before make a choice." + 
						"The process Online Service must make a choice after the activity check open orders and before the activity prepare warm goods and before the activity collect cold goods." + 
						"The choice is: “Check goods ordered”." + 
						"In the first case the process Online Service performs the activity by prepare warm goods after make a choice and before complete the choice." + 
						"In the second case the process Online Service performs the activity by collect cold goods after make a choice and before complete the choice." + 
						"The process Online Service completes the choice after the activity prepare warm goods and after the activity collect cold goods and before perform a send message order." + 
						"The process Online Service performs a send message order by send order after complete the choice and before wait." + 
						"The process Customer performs a receive message order by order received after wait and before receive a message email." + 
						"The process Online Service waits by 1 day after perform a send message order and before send a message email." + 
						"The process Online Service sends a message email by send email after wait and before end." + 
						"The process Online service ends by order completed after send a message email." + 
						"The process Customer receives a message email by receive email after perform a receive message order and before the end." + 
						"The process Customer ends by order received after receive a message email." +
						"The process Customer sends a message ID MESSAGGIO and ends by LABEL MESSAGGIO after receive a message email." +
						"In the fourth case the process Customer ends by order received after receive a message email."+
						"In the millesima volta case the process Online Service performs a send message ID MESSAGGIO by LABEL MESSAGGIO after complete the choice and before wait."+
						"In the settecentocinquantesimo case the process Customer completes the decision after receive a message confirmation and before wait and before wait."+
						"The process Customer completes the decision after receive a message confirmation and before wait and before wait."+
						"In the first case the process Customer must take a parallel selection after receive messasge."+
						"The process Customer must take a parallel selection after receive messasge."+
						"The process PROCESSO completes the selection after the task."+
						"In the 8726437284 case the process NOME PROCESSO completes the selection after the task."+
						"The process NOME PROCESSOOOO must make a choice after make a decision."+
						"The process PROCESS NAMEEEEE completes the choice after send a message."+
						"In the 7234672346 case the process NOME PROCESSOOOO must make a choice after make a decision."+
						"In the fith case the process PROCESS NAMEEEEE completes the choice after send a message."+
						"the process PROVA PROCESSO expects a reply after send a message and before receive a message."+
						"In the tenth case the process NOME DI PROVA DI UN PROCESSO expects a reply after take a decision and before receive a message." +
						"In the first case, the process Customer sends a message ID MESSAGGIO and ends by LABEL MESSAGGIO after receive a message email."+
						"The process PROVA begins every april on 30th before taks accept order.";
				
				String common = "The process "; // Questa stringa è in comune a tutte le frasi per cui la ritroverò in ogni elemento BPMN
				
				String pool = "is a pool"; // La stringa "common" unita a questa mi da l'elemento POOL
				String Bpool = "is a black"; // La stringa "common" unita a questa mi da l'elemento BLACK POOL
				String BpoolMsg = "catches the message";
			
				String start = "starts "; // La stringa "common" unita a questa mi da l'elemento START EVENT
				String msgR = "receives a message "; // La stringa "common" unita a questa mi da l'elemento INTERMEDIATE RECEIVE MESSAGE. Se poi in più unisco anche la stringa "start", mi da l'elemento START-MESSAGE.
				
				String end = "ends "; // La stringa "common" unita a questa mi da l'elemento END EVENT
				String msgS = "sends a message "; // La stringa "common" unita a questa mi da l'elemento INTERMEDIATE SEND MESSAGE. Se poi in più unisco anche la stringa "end", mi da l'elemento END-MESSAGE.
				String and ="and ends"; //questa variabile la uso per poter distinguere la generazione di un  END MESSAGE-EVENT da un INTERMEDIATE SEND MESSAGE-EVENT
				
				String task = "performs the activity"; // La stringa "common" unita a questa mi da l'elemento TASK
				String taskS = "performs a send message "; // La stringa "common" unita a questa mi da l'elemento TASK-SEND MESSAGE
				String taskR = "performs a receive message "; // La stringa "common" unita a questa mi da l'elemento TASK-RECEIVE MESSAGE
				
				String xorS = "must take a decision"; // La stringa "common" unita a questa mi da l'elemento XOR SPLIT
				String xorJ = "completes the decision"; // La stringa "common" unita a questa mi da l'elemento XOR JOIN
				String decision = "The decision is:";
				
				String andS = "must take a parallel selection"; // La stringa "common" unita a questa mi da l'elemento AND SPLIT
				String andJ = "completes the selection"; // La stringa "common" unita a questa mi da l'elemento AND JOIN
				
				String inclS = "must make a choice"; // La stringa "common" unita a questa mi da l'elemento INCLUSIVE SPLIT
				String inclJ = "completes the choice"; // La stringa "common" unita a questa mi da l'elemento INCLUSIVE JOIN
				String choice = "The choice is:";
				
				String eventBased = "expects a reply"; // La stringa "common" unita a questa mi da l'elemento EVENT-BASED GATEWAY
				
				String timerStart1 = "begins at"; // La stringa "common" unita a questa mi da l'elemento START TIMER EVENT
				String timerStart2 = "begins in"; // La stringa "common" unita a questa mi da l'elemento START TIMER EVENT
				String timerStart3 = "begins every"; // La stringa "common" unita a questa mi da l'elemento START TIMER EVENT
				
				String timerIntermediate = "waits by"; // La stringa "common" unita a questa mi da l'elemento INTEMREDIATE TIMER EVENT
				
				String path1 = "In the";
				String path2 = "case";
				
				String intrLabel = "by ";
				String conj = "and ";
				
				String before = "before ";
				String after = "after ";
				
				String[] result = input.split("\\."); // creo un array di stringhe al cui interno vi è il testo preso in input suddiviso in frasi (stringhe) separate dal punto 
			
				for (String inputStr:result) { // per ogni frase delimitata dal punto vado ad applicare le regole qui sotto					
						
					if(inputStr.contains(common)&&inputStr.contains(pool)) { // se nella frase trovo "The process" + "is a pool"
						
							String processName = inputStr.substring((inputStr.indexOf(common.toLowerCase())+common.length()), inputStr.indexOf(pool));
							
					//		System.out.println("\n" + "POOL:" + inputStr);// stampa POOL
					//		System.out.println("POOL NAME:"+ processName.trim());
						
						
					}	
										
					else if(inputStr.contains(common)&&inputStr.contains(Bpool)) { // se nella frase trovo "The process" + "is a black"	
						
					//	String processName = inputStr.substring((inputStr.indexOf(common.toLowerCase())+common.length()), inputStr.indexOf(Bpool));
						
					//	System.out.println("\n" + "BLACKPOOL:" + inputStr); // stampa BLACKPOOL
					//	System.out.println("BLACKPOOL NAME:"+processName.trim());
					}
					
					else if(inputStr.contains(common)&&inputStr.contains(BpoolMsg)) { // se nella frase trovo "The process" + "catches the message"	
						
					//	String processName = inputStr.substring((inputStr.indexOf(common.toLowerCase())+common.length()), inputStr.indexOf(BpoolMsg));
					//	String idMsgR = inputStr.substring(inputStr.indexOf(BpoolMsg)+BpoolMsg.length(), inputStr.length());
						
					//	System.out.println("\n" + "BLACKPOOL CATCHING MESSAGE:" + inputStr); // stampa BLACKPOOL CATCHING MESSAGE
					//	System.out.println("BLACKPOOL NAME:"+processName.trim());
					//	System.out.println("ID-MESSAGE:"+idMsgR.trim());
					}
					
					else if(inputStr.contains(common)&&inputStr.contains(start)&&inputStr.contains(msgR)) { // se nella frase trovo "The process" + "starts" + "receives a message"	
						
						//	String processName = inputStr.substring((inputStr.indexOf(common.toLowerCase())+common.length()), inputStr.indexOf(msgR));
						//	String idMsgReceive =inputStr.substring(inputStr.indexOf(msgR)+msgR.length(), inputStr.indexOf(conj));
						//	String label = inputStr.substring(inputStr.indexOf(intrLabel)+intrLabel.length(), inputStr.indexOf(before));
						
						//		System.out.println("\n" + "START RECEIVE-MESSAGE:" + inputStr); // stampa START RECEIVE-MESSAGE
						//		System.out.println("PROCESS NAME:"+ processName.trim());
						//		System.out.println("ID-MESSAGE:"+idMsgReceive.trim());
						//		System.out.println("LABEL:"+label.trim());
					
					}
					
					else if(inputStr.contains(common)&&inputStr.contains(msgR)||(inputStr.contains(common.toLowerCase())&&inputStr.contains(msgR))) { // se nella frase trovo "The process"/"the process" + "receives a message"
						
						//		String processName = inputStr.substring((inputStr.indexOf(common.toLowerCase())+common.length()), inputStr.indexOf(msgR));
						//		String idMsgReceive = inputStr.substring(inputStr.indexOf(msgR)+msgR.length(), inputStr.indexOf(intrLabel));
								String label = inputStr.substring(inputStr.indexOf(intrLabel)+intrLabel.length(), inputStr.indexOf(after));
						
						//		System.out.println("\n" + "INTERMEDIATE RECEIVE-MESSAGE:" + inputStr); // stampa INTERMEDIATE RECEIVE-MESSAGE
						//		System.out.println("PROCESS NAME:"+ processName.trim());
						//		System.out.println("ID-MESSAGE:" + idMsgReceive.trim());
						//		System.out.println("LABEL:" + label.trim());
						
								
						IntermediateCatchEvent intermediateCatchEvent = helpClass.createElement(helpClass.process, label.replace(" ", "")+label.valueOf(random.nextInt()).replace(" ", ""), label, IntermediateCatchEvent.class,
								helpClass.plane, 250, 250, 50, 50, true);
								
					}
					
					else if(inputStr.contains(common)&&inputStr.contains(start)||(inputStr.contains(common.toLowerCase())&&inputStr.contains(start))) { // se nella frase trovo "The process"/"the process" + "starts"
						
							String processName = inputStr.substring(inputStr.indexOf(common.toLowerCase())+common.length(), inputStr.indexOf(start));
							String label = inputStr.substring((inputStr.indexOf(intrLabel)+intrLabel.length()), inputStr.indexOf(before));
						
						//	System.out.println("\n" + "START EVENT:" + inputStr); // stampa START EVENT
						//	System.out.println("PROCESS NAME:"+processName.trim());
						//	System.out.println("LABEL:"+label.trim());
							
						StartEvent startEvent = helpClass.createElement(helpClass.process, "start", label, StartEvent.class,
								helpClass.plane, 15, 15, 50, 50, true); 
						
								
					

					}
						
					else if(inputStr.contains(common)&&inputStr.contains(msgS)&&inputStr.contains(and)||(inputStr.contains(common.toLowerCase())&&inputStr.contains(msgS))&&inputStr.contains(and)) { // se nella frase trovo "The process"/"the process" + "sends a message" + "and ends"
						
							String processName = inputStr.substring(inputStr.indexOf(common.toLowerCase())+common.length(), inputStr.indexOf(msgS));
							String idMsgSend = inputStr.substring(inputStr.indexOf(msgS)+msgS.length(), inputStr.indexOf(conj));
							String label = inputStr.substring(inputStr.indexOf(intrLabel)+intrLabel.length(), inputStr.indexOf(after));
						
						//		System.out.println("\n" + "END SEND-MESSAGE:" + inputStr); // stampa END SEND-MESSAGE
						//		System.out.println("PROCESS NAME:" + processName.trim());
						//		System.out.println("ID-MESSAGE:" + idMsgSend.trim());
						//		System.out.println("LABEL:" + label.trim());
							
					}
						
					else if(inputStr.contains(common)&&inputStr.contains(msgS)||(inputStr.contains(common.toLowerCase())&&inputStr.contains(msgS))) { // se nella frase trovo "The process"/"the process" + "sends a message"
															
								String processName = inputStr.substring(inputStr.indexOf(common.toLowerCase())+common.length(), inputStr.indexOf(msgS));
								String idMsgSend = inputStr.substring(inputStr.indexOf(msgS)+msgS.length(), inputStr.indexOf(intrLabel));
								String label = inputStr.substring(inputStr.indexOf(intrLabel)+intrLabel.length(), inputStr.indexOf(after));
							
						//		System.out.println("\n" + "INTERMEDIATE SEND-MESSAGE:" + inputStr); // stampa INTERMEDIATE SEND-MESSAGE
						//		System.out.println("PROCESS NAME:" + processName.trim());
						//		System.out.println("ID-MESSAGE:" + idMsgSend.trim());
						//		System.out.println("LABEL:" + label.trim());
							
							
					}
					
					else if(inputStr.contains(common)&&inputStr.contains(end)||(inputStr.contains(common.toLowerCase())&&inputStr.contains(end))) { // se nella frase trovo "The process"/"the process" + "ends"
						
							String processName = inputStr.substring(inputStr.indexOf(common.toLowerCase())+common.length(), inputStr.indexOf(end));
							String label = inputStr.substring(inputStr.indexOf(intrLabel)+intrLabel.length(), inputStr.indexOf(after));

							
							
						//	System.out.println("\n" + "END EVENT:" + inputStr); // stampa END EVENT
						//	System.out.println("PROCESS NAME:" + processName.trim());
						//	System.out.println("LABEL:" + label.trim());
							
							EndEvent EndEvent = helpClass.createElement(helpClass.process, label.replace(" ", "")+label.valueOf(random.nextInt()).replace(" ", ""), label, EndEvent.class,
									helpClass.plane, 15, 15, 50, 50, true);
					
					}
					
					else if(inputStr.contains(common)&&inputStr.contains(task)||(inputStr.contains(common.toLowerCase())&&inputStr.contains(task))) { // se nella frase trovo "The process"/"the process" + "performs the activity"	
						
							String processName = inputStr.substring(inputStr.indexOf(common.toLowerCase())+common.length(), inputStr.indexOf(task));
							String label = inputStr.substring(inputStr.indexOf(intrLabel)+intrLabel.length(), inputStr.indexOf(after));
								
						//	System.out.println("\n" + "TASK:" + inputStr); // stampa TASK 
						//	System.out.println("PROCESS NAME:" + processName.trim());
						//	System.out.println("LABEL:" + label.trim());
								
							Task Task = helpClass.createElement(helpClass.process, label.replace(" ", "")+label.valueOf(random.nextInt()).replace(" ", ""), label, Task.class,
									helpClass.plane, 25, 25, 80, 80, true);
									
					}
					
					else if(inputStr.contains(common)&&inputStr.contains(taskS)||(inputStr.contains(common.toLowerCase())&&inputStr.contains(taskS))) { // se nella frase trovo "The process"/"the process" + "performs a send message"	
												
							String processName = inputStr.substring(inputStr.indexOf(common.toLowerCase())+common.length(), inputStr.indexOf(taskS));
							String idMsgSend = inputStr.substring(inputStr.indexOf(taskS)+taskS.length(), inputStr.indexOf(intrLabel));
							String label = inputStr.substring(inputStr.indexOf(intrLabel)+intrLabel.length(), inputStr.indexOf(after));
						
						//	System.out.println("\n" + "SEND-TASK:" + inputStr); // stampa SEND-TASK
						//	System.out.println("PROCESS NAME:"+ processName.trim());
						//	System.out.println("ID-MESSAGE:" + idMsgSend.trim());
						//	System.out.println("LABEL:" + label.trim());	
								
							SendTask sendTask = helpClass.createElement(helpClass.process, label.replace(" ", "")+label.valueOf(random.nextInt()).replace(" ", ""), label, SendTask.class,
									helpClass.plane, 25, 25, 80, 80, true);
							
					}					
					
					else if(inputStr.contains(common)&&inputStr.contains(taskR)||(inputStr.contains(common.toLowerCase())&&inputStr.contains(taskR))) { // se nella frase trovo "The process"/"the process" + "performs a receive message"	
						
							String processName = inputStr.substring(inputStr.indexOf(common.toLowerCase())+common.length(), inputStr.indexOf(taskR));
							String idMsgR = inputStr.substring(inputStr.indexOf(taskR)+taskR.length(), inputStr.indexOf(intrLabel));
							String label = inputStr.substring(inputStr.indexOf(intrLabel)+intrLabel.length(), inputStr.indexOf(after));

						//	System.out.println("\n" + "RECEIVE-TASK:" + inputStr); // stampa RECEIVE-TASK
						//	System.out.println("PROCESS NAME:"+ processName.trim());
						//	System.out.println("ID-MESSAGE:" + idMsgR.trim());
						//	System.out.println("LABEL:" + label.trim());
					}
					
					else if(inputStr.contains(common)&&inputStr.contains(xorS)||(inputStr.contains(common.toLowerCase())&&inputStr.contains(xorS))) { // se nella frase trovo "The process"/"the process" + "must take a decision"		
									
							String label = inputStr.substring(inputStr.indexOf(xorS.toLowerCase())+xorS.length()+2, inputStr.indexOf(after)-2);
							String processName = inputStr.substring(inputStr.indexOf(common.toLowerCase())+common.length(), inputStr.indexOf(xorS));
						
						//	System.out.println("\n" + "XOR-SPLIT:" + inputStr); // stampa XOR-SPLIT
						//	System.out.println("PROCESS NAME:"+ processName.trim());
							
							ExclusiveGateway exclusiveSplit = helpClass.createElement(helpClass.process, "exclusiveSplit"+"exclusiveSplit".valueOf(random.nextInt()).replace(" ", "") , label,
									ExclusiveGateway.class, helpClass.plane, 120, 120, 50, 50, true); 
							
					}
					
					else if(inputStr.contains(common)&&inputStr.contains(xorJ)||(inputStr.contains(common.toLowerCase())&&inputStr.contains(xorJ))) { // se nella frase trovo "The process"/"the process" + "completes the decision"		
						
							String processName = inputStr.substring(inputStr.indexOf(common.toLowerCase())+common.length(), inputStr.indexOf(xorJ));
						
						//	System.out.println("\n" + "XOR-JOIN:" + inputStr); // stampa XOR-JOIN
						//	System.out.println("PROCESS NAME:"+ processName.trim());
								
					}					
					
					else if(inputStr.contains(decision)||(inputStr.contains(decision.toLowerCase()))) { // se nella frase trovo "The decision is:"/"the decision is:" 	
						
							String label = inputStr.substring(inputStr.indexOf(decision.toLowerCase())+decision.length()+1, inputStr.length());
						
						//	System.out.println("\n" + "XOR-LABEL:" + inputStr); // stampa XOR-LABEL
						//	System.out.println("LABEL:" + label.trim());

					}
					
					else if(inputStr.contains(common)&&inputStr.contains(andS)||(inputStr.contains(common.toLowerCase())&&inputStr.contains(andS))) { // se nella frase trovo "The process"/"the process" + "must take a parallel selection"		
						
							String processName = inputStr.substring(inputStr.indexOf(common.toLowerCase())+common.length(), inputStr.indexOf(andS));
						
						//	System.out.println("\n" + "AND-SPLIT:" + inputStr); // stampa AND-SPLIT
						//	System.out.println("PROCESS NAME:"+ processName.trim());
					}
					
					else if(inputStr.contains(common)&&inputStr.contains(andJ)||(inputStr.contains(common.toLowerCase())&&inputStr.contains(andJ))) { // se nella frase trovo "The process"/"the process" + "completes the selection"		
						
							String processName = inputStr.substring(inputStr.indexOf(common.toLowerCase())+common.length(), inputStr.indexOf(andJ));
							
						//		System.out.println("\n" + "AND-JOIN:" + inputStr); // stampa AND-JOIN
						//		System.out.println("PROCESS NAME:"+ processName.trim());
							
					}					
					
					else if(inputStr.contains(common)&&inputStr.contains(inclS)||(inputStr.contains(common.toLowerCase())&&inputStr.contains(inclS))) { // se nella frase trovo "The process"/"the process" + "must make a choice"		
			
							String processName = inputStr.substring(inputStr.indexOf(common.toLowerCase())+common.length(), inputStr.indexOf(inclS));
						
						//	System.out.println("\n" + "INCLUSIVE-SPLIT:" + inputStr); // stampa INCLUSIVE-SPLIT
						//	System.out.println("PROCESS NAME:" + processName.trim());

					}
					
					else if(inputStr.contains(common)&&inputStr.contains(inclJ)||(inputStr.contains(common.toLowerCase())&&inputStr.contains(inclJ))) { // se nella frase trovo "The process"/"the process" + "completes the choice"		
						
							String processName = inputStr.substring(inputStr.indexOf(common.toLowerCase())+common.length(), inputStr.indexOf(inclJ));
						
						//	System.out.println("\n" + "INCLUSIVE-JOIN:" + inputStr); // stampa INCLUSIVE-JOIN
						//	System.out.println("PROCESS NAME:" + processName.trim());
						
					}					
					
					else if(inputStr.contains(choice)||(inputStr.contains(choice.toLowerCase()))) { // se nella frase trovo "The choice is:"/"the choice is:" 	
						
							String label = inputStr.substring(inputStr.indexOf(choice.toLowerCase())+choice.length()+1, inputStr.length());
						
						//	System.out.println("\n" + "INCLUSIVE-LABEL:" + inputStr); // stampa INCLUSIVE-LABEL
						//	System.out.println("LABEL:" + label.trim());
					}
					
					
					else if(inputStr.contains(common)&&inputStr.contains(eventBased)||(inputStr.contains(common.toLowerCase())&&inputStr.contains(eventBased))) { // se nella frase trovo "The process"/"the process" + "expects a reply"		
						
							String processName = inputStr.substring(inputStr.indexOf(common.toLowerCase())+common.length(), inputStr.indexOf(eventBased));
						
						//	System.out.println("\n" + "EVENT-BASED GATEWAY:" + inputStr); // stampa EVENT-BASED GATEWAY
						//	System.out.println("PROCESS NAME:" + processName.trim());
						
							EventBasedGateway exclusiveSplit = helpClass.createElement(helpClass.process, "eventBasedSplit"+"eventBasedSplit".valueOf(random.nextInt()).replace(" ", ""), "",
									EventBasedGateway.class, helpClass.plane, 500, 500, 50, 50, true);  
							
					}		
					
					else if(inputStr.contains(common)&&inputStr.contains(timerStart1)||inputStr.contains(common.toLowerCase())&&inputStr.contains(timerStart1)) { // se nella frase trovo "The process"/"the process" + "begins at"
						
							String processName1 = inputStr.substring(inputStr.indexOf(common.toLowerCase())+common.length(), inputStr.indexOf(timerStart1));
							String label = inputStr.substring(inputStr.indexOf(timerStart1)+timerStart1.length(), inputStr.indexOf(before));

						//System.out.println("\n" + "START-TIMER EVENT:" + inputStr); // stampa START-TIMER EVENT
						//System.out.println("PROCESS NAME:" + processName1.trim());
						//System.out.println("LABEL:" + label.trim());
					}
					
					else if(inputStr.contains(common)&&inputStr.contains(timerStart2)||inputStr.contains(common.toLowerCase())&&inputStr.contains(timerStart2)){ // se nella frase trovo "The process"/"the process" + "begins in"		
						
							String processName2 = inputStr.substring(inputStr.indexOf(common.toLowerCase())+common.length(), inputStr.indexOf(timerStart2));
							String label = inputStr.substring(inputStr.indexOf(timerStart2)+timerStart2.length(), inputStr.indexOf(before));
						
						//System.out.println("\n" + "START-TIMER EVENT:" + inputStr); // stampa START-TIMER EVENT
						//System.out.println("PROCESS NAME:" + processName2.trim());
						//System.out.println("LABEL:" + label.trim());

					}
					
					else if(inputStr.contains(common)&&inputStr.contains(timerStart3)||inputStr.contains(common.toLowerCase())&&inputStr.contains(timerStart3)){ // se nella frase trovo "The process"/"the process" + "begins every"		
						
							String processName3 = inputStr.substring(inputStr.indexOf(common.toLowerCase())+common.length(), inputStr.indexOf(timerStart3));
							String label = inputStr.substring(inputStr.indexOf(timerStart3)+timerStart3.length(), inputStr.indexOf(before));
						
						//System.out.println("\n" + "START-TIMER EVENT:" + inputStr); // stampa START-TIMER EVENT
						//System.out.println("PROCESS NAME:" + processName3.trim());
						//System.out.println("LABEL:" + label.trim());
					}
					
					else if(inputStr.contains(common)&&inputStr.contains(timerIntermediate)||inputStr.contains(common.toLowerCase())&&inputStr.contains(timerIntermediate)) { // se nella frase trovo "The process"/"the process" + "waits by"		
					
							String processName = inputStr.substring(inputStr.indexOf(common.toLowerCase())+common.length(), inputStr.indexOf(timerIntermediate));
							String label = inputStr.substring(inputStr.indexOf(timerIntermediate)+timerIntermediate.length(), inputStr.indexOf(after));
						
						//	System.out.println("\n" + "INTERMEDIATE-TIMER EVENT:" + inputStr); // stampa INTERMEDIATE-TIMER EVENT
						//	System.out.println("PROCESS NAME:" + processName.trim());
						//	System.out.println("LABEL:" + label.trim());

					}			
								 
					else {System.out.println("\n" + "HAI SCRITTO MALE QUESTO ELEMENTO: "+ inputStr);
					
					}
					
			}
				
			
				
				//System.out.println("\n" + "------TOTALE ELEMENTI:------ " + result.length);	
				Bpmn.validateModel(helpClass.modelInstance);
				File file = File.createTempFile("bpmn-model-api-", ".bpmn");
				Bpmn.writeModelToFile(file, helpClass.modelInstance); 
	}
					
					
}

