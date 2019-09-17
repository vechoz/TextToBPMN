package Prova.Prova;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BpmnModelElementInstance;
import org.camunda.bpm.model.bpmn.instance.Definitions;
import org.camunda.bpm.model.bpmn.instance.EndEvent;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.ParallelGateway;
import org.camunda.bpm.model.bpmn.instance.ServiceTask;
import org.camunda.bpm.model.bpmn.instance.StartEvent;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnDiagram;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnPlane;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperties;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaIn;
import java.io.File;
import java.io.IOException;

import org.camunda.bpm.*;


public class FluentTextBPM {

	public static void main(String[] args) throws IOException {
	
	BpmnModelInstance myProcess = Bpmn.createExecutableProcess("process-payments")
		      .startEvent().id("start")
	.done();
	
	StartEvent startEvent = myProcess.getModelElementById("start");
	startEvent.builder().connectTo("start")
	
	.eventBasedGateway()
    .sendTask()
    .exclusiveGateway()
    .name("cos faccio?")
    .inclusiveGateway()
    .intermediateCatchEvent("provba")
    .serviceTask()
        .name("Paga on line")
    .endEvent()
    .done();
	
	BpmnModelInstance myProcess2 = Bpmn.createExecutableProcess("process-payments").startEvent();
	Bpmn.validateModel((BpmnModelInstance) startEvent);
	
	File file = File.createTempFile("bpmn-model-api-", ".bpmn");
	Bpmn.writeModelToFile(file,(BpmnModelInstance) startEvent );

	}
}
