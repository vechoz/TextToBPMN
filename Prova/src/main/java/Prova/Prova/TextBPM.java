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
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;

import java.io.File;
import java.io.IOException;

import org.camunda.bpm.*;

public class TextBPM {

	public static void main(String[] args) throws IOException {

		HelpClass helpClass = new HelpClass();
		helpClass.create();

		// create the elements

		StartEvent startEvent = helpClass.createElement(helpClass.process, "start", "Start label", StartEvent.class,
				helpClass.plane, 15, 15, 50, 50, true);

		UserTask task = helpClass.createElement(helpClass.process, "userTask", "Task label", UserTask.class,
				helpClass.plane, 100, 0, 80, 100, false);

		EndEvent endEvent = helpClass.createElement(helpClass.process, "end", "End label", EndEvent.class,
				helpClass.plane, 250, 15, 50, 50, true);

		// create the connections between the elements

		helpClass.createSequenceFlow(helpClass.process, startEvent, task, helpClass.plane, 65, 40, 100, 40);
		helpClass.createSequenceFlow(helpClass.process, task, endEvent, helpClass.plane, 200, 40, 250, 40);

		// validate and write model to file

		Bpmn.validateModel(helpClass.modelInstance);
		File file = File.createTempFile("bpmn-model-api-", ".bpmn");
		Bpmn.writeModelToFile(file, helpClass.modelInstance);

	}

}
