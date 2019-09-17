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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.camunda.bpm.*;

public class TextToBPMexample {

	public static void main(String[] args) throws IOException {

		HelpClass helpClass = new HelpClass();
		helpClass.create();

		// create the elements

		StartEvent startEvent = helpClass.createElement(helpClass.process, "start", "Start label", StartEvent.class,
				helpClass.plane, 15, 15, 50, 50, true);
		ParallelGateway parallelSplit = helpClass.createElement(helpClass.process, "parallelSplit",
				"Parallel Split label", ParallelGateway.class, helpClass.plane, 30, 30, 50, 50, true);
		ServiceTask serviceTask = helpClass.createElement(helpClass.process, "serviceTask", "Service Task label",
				ServiceTask.class, helpClass.plane, 40, 40, 50, 50, true);
		UserTask userTask = helpClass.createElement(helpClass.process, "userTask", "User Task label", UserTask.class,
				helpClass.plane, 50, 50, 50, 50, true);
		ParallelGateway parallelJoin = helpClass.createElement(helpClass.process, "parallelJoin", "Parallel Join label",
				ParallelGateway.class, helpClass.plane, 60, 60, 50, 50, true);
		EndEvent endEvent = helpClass.createElement(helpClass.process, "end", "End label", EndEvent.class,
				helpClass.plane, 70, 70, 50, 50, true);

		// create the connections between the elements

		helpClass.createSequenceFlow(helpClass.process, startEvent, parallelSplit, helpClass.plane, 65, 40, 100, 40);
		helpClass.createSequenceFlow(helpClass.process, parallelSplit, serviceTask, helpClass.plane, 65, 40, 100, 40);
		helpClass.createSequenceFlow(helpClass.process, parallelSplit, userTask, helpClass.plane, 65, 40, 100, 40);
		helpClass.createSequenceFlow(helpClass.process, serviceTask, parallelJoin, helpClass.plane, 65, 40, 100, 40);
		helpClass.createSequenceFlow(helpClass.process, userTask, parallelJoin, helpClass.plane, 65, 40, 100, 40);
		helpClass.createSequenceFlow(helpClass.process, parallelJoin, endEvent, helpClass.plane, 65, 40, 100, 40);

		// validate and write model to file

		Bpmn.validateModel(helpClass.modelInstance);
		File file = File.createTempFile("bpmn-model-api-", ".bpmn");
		Bpmn.writeModelToFile(file, helpClass.modelInstance);

	}

}
