package main.java.com.nokia.ros.nodes;

import org.ros.message.MessageListener;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Subscriber;


public class Position extends AbstractNodeMain {
	public static turtlesim.Pose turtlesimPose;

	public GraphName getDefaultNodeName() {
		return GraphName.of("rosjava/turtlesim_listeners");
	}

	public void onStart(ConnectedNode connectedNode) {
		Subscriber<turtlesim.Pose> poseSubscriber = connectedNode.newSubscriber("/turtle1/pose", turtlesim.Pose._TYPE);
		poseSubscriber.addMessageListener(new MessageListener<turtlesim.Pose>() {

			public void onNewMessage(turtlesim.Pose poseMessage) {
				// TODO Auto-generated method stub
				turtlesimPose = poseMessage;
				System.out.printf("Pose  %2f %2f %s\n", poseMessage.getX(), poseMessage.getY(), poseMessage.getTheta());

			}
		});
	}
}
