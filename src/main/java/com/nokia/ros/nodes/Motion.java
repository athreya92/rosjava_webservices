package main.java.com.nokia.ros.nodes;

import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Publisher;

public class Motion extends AbstractNodeMain {
	
	private String direction;
	private double speed;
	
	public Motion(String direction, double speed) {
		this.direction = direction;
		this.speed = speed;
	}

	@Override
	public GraphName getDefaultNodeName() {
		return GraphName.of("rosjava/turtlesimMotion");
	}

	@Override
	public void onStart(ConnectedNode connectedNode) {
		final Publisher<geometry_msgs.Twist> publisher = connectedNode.newPublisher("turtle1/cmd_vel", geometry_msgs.Twist._TYPE);
		geometry_msgs.Twist twist = publisher.newMessage();
		
		if(this.direction == "back") {
			twist.getLinear().setX(this.speed*-1);
			twist.getLinear().setY(0);
			twist.getLinear().setZ(0);
			
			twist.getAngular().setX(0);
			twist.getAngular().setY(0);
			twist.getAngular().setZ(0);
		}
		if(this.direction == "front") {
			twist.getLinear().setX(this.speed);
			twist.getLinear().setY(0);
			twist.getLinear().setZ(0);
			
			twist.getAngular().setX(0);
			twist.getAngular().setY(0);
			twist.getAngular().setZ(0);
		}
		if(this.direction == "left") {
			twist.getLinear().setX(0);
			twist.getLinear().setY(0);
			twist.getLinear().setZ(0);
			
			twist.getAngular().setX(0);
			twist.getAngular().setY(0);
			twist.getAngular().setZ(this.speed*-1);
		}
		if(this.direction == "right") {
			twist.getLinear().setX(0);
			twist.getLinear().setY(0);
			twist.getLinear().setZ(0);
			
			twist.getAngular().setX(0);
			twist.getAngular().setY(0);
			twist.getAngular().setZ(this.speed);
		}
		try {
			Thread.sleep(300);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		publisher.publish(twist);
	}
}
