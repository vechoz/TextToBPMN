package Prova.Prova;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BaseElement;
import org.camunda.bpm.model.bpmn.instance.BpmnModelElementInstance;
import org.camunda.bpm.model.bpmn.instance.Definitions;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnDiagram;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnEdge;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnLabel;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnPlane;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;
import org.camunda.bpm.model.bpmn.instance.dc.Bounds;
import org.camunda.bpm.model.bpmn.instance.di.Waypoint;

public class HelpClass {

	public BpmnModelInstance modelInstance;
	public Definitions definitions;
	public Process process;
	public BpmnPlane plane;

	public class create {}

	public void create() {

		modelInstance = Bpmn.createEmptyModel();

		definitions = modelInstance.newInstance(Definitions.class);
		definitions.setTargetNamespace("http://camunda.org/examples");
		modelInstance.setDefinitions(definitions);

		process = modelInstance.newInstance(Process.class);
		process.setId("process");
		definitions.addChildElement(process);

		BpmnDiagram diagram = modelInstance.newInstance(BpmnDiagram.class);
		plane = modelInstance.newInstance(BpmnPlane.class);
		plane.setBpmnElement(process);
		diagram.setBpmnPlane(plane);
		definitions.addChildElement(diagram);

	}

	// public <T extends BpmnModelElementInstance> T
	// createElement(BpmnModelElementInstance parentElement, String id, Class<T>
	// elementClass) {
	// T element = modelInstance.newInstance(elementClass);
	// element.setAttributeValue("id", id, true);
	// parentElement.addChildElement(element);
	// return element;
	// }

	public <T extends BpmnModelElementInstance> T createElement(BpmnModelElementInstance parentElement, String id,
			String name, Class<T> elementClass, BpmnPlane plane, double x, double y, double heigth, double width,
			boolean withLabel) {
		T element = modelInstance.newInstance(elementClass);
		element.setAttributeValue("id", id, true);
		element.setAttributeValue("name", name, false);
		parentElement.addChildElement(element);

		BpmnShape bpmnShape = modelInstance.newInstance(BpmnShape.class);
		bpmnShape.setBpmnElement((BaseElement) element);

		Bounds bounds = modelInstance.newInstance(Bounds.class);
		bounds.setX(x);
		bounds.setY(y);
		bounds.setHeight(heigth);
		bounds.setWidth(width);
		bpmnShape.setBounds(bounds);

		if (withLabel) {
			BpmnLabel bpmnLabel = modelInstance.newInstance(BpmnLabel.class);
			Bounds labelBounds = modelInstance.newInstance(Bounds.class);
			labelBounds.setX(x);
			labelBounds.setY(y + heigth);
			labelBounds.setHeight(heigth);
			labelBounds.setWidth(width);
			bpmnLabel.addChildElement(labelBounds);
			bpmnShape.addChildElement(bpmnLabel);
		}
		plane.addChildElement(bpmnShape);

		return element;
	}

	// public SequenceFlow createSequenceFlow(Process process, FlowNode from,
	// FlowNode to) {
	// String identifier = from.getId() + "-" + to.getId();
	// SequenceFlow sequenceFlow = createElement(process, identifier,
	// SequenceFlow.class);
	// process.addChildElement(sequenceFlow);
	// sequenceFlow.setSource(from);
	// from.getOutgoing().add(sequenceFlow);
	// sequenceFlow.setTarget(to);
	// to.getIncoming().add(sequenceFlow);
	// return sequenceFlow;
	// }

	public SequenceFlow createSequenceFlow(Process process, FlowNode from, FlowNode to, BpmnPlane plane,
			int... waypoints) {
		String identifier = from.getId() + "-" + to.getId();
		SequenceFlow sequenceFlow = modelInstance.newInstance(SequenceFlow.class);
		sequenceFlow.setAttributeValue("id", identifier, true);
		process.addChildElement(sequenceFlow);
		sequenceFlow.setSource(from);
		from.getOutgoing().add(sequenceFlow);
		sequenceFlow.setTarget(to);
		to.getIncoming().add(sequenceFlow);

		BpmnEdge bpmnEdge = modelInstance.newInstance(BpmnEdge.class);
		bpmnEdge.setBpmnElement(sequenceFlow);
		for (int i = 0; i < waypoints.length / 2; i++) {
			double waypointX = waypoints[i * 2];
			double waypointY = waypoints[i * 2 + 1];
			Waypoint wp = modelInstance.newInstance(Waypoint.class);
			wp.setX(waypointX);
			wp.setY(waypointY);
			bpmnEdge.addChildElement(wp);
		}
		plane.addChildElement(bpmnEdge);

		return sequenceFlow;
	}

}
