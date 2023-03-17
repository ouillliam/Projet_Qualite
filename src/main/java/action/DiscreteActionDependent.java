package action;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.TreeSet;

import timer.Timer;

/**
 * @author flver
 *
 */

public class DiscreteActionDependent implements DiscreteActionInterface {
	
	protected DiscreteActionInterface baseAction;
	protected TreeSet<DiscreteAction> depedentActions;
	private Iterator<DiscreteAction> it;
	protected DiscreteActionInterface currentAction;
	
	
	/**
	 * Construct a series of dependent actions, first action (method) called is baseMethodName, then method nextMethod() is called to select the next action. 
	 * 
	 * @param o
	 * @param baseMethodName
	 * @param timerBase
	 */	
	public DiscreteActionDependent(Object o, String baseMethodName, Timer timerBase){
		this.baseAction = new DiscreteAction(o, baseMethodName, timerBase);
		this.depedentActions = new TreeSet<>();
		this.it = this.depedentActions.iterator();
		this.currentAction = this.baseAction;
	}
	
	public void addDependence(Object o, String depentMethodName, Timer timerDependence) {
		this.depedentActions.add(new DiscreteAction(o, depentMethodName, timerDependence));
	}
	
	private void reInit() {
		for (Iterator<DiscreteAction> iter = this.depedentActions.iterator(); iter.hasNext(); ) {
		    DiscreteAction element = iter.next();
		}		
	}
	
	public void nextMethod(){
		if (this.currentAction == this.baseAction){
			this.it = this.depedentActions.iterator();
			this.currentAction = this.it.next();
		}else if(this.currentAction == this.depedentActions.last()){
			this.currentAction = this.baseAction;
			this.reInit();
		}else {
			this.currentAction = this.it.next();
		}
	}
	
	public void spendTime(int t) {
		for (Iterator<DiscreteAction> iter = this.depedentActions.iterator(); iter.hasNext(); ) {
		    iter.next();
		}
	}

	public void updateTimeLaps() {
		// time laps is updated at the re-initialization
		this.nextMethod();	
	}

	public Method getMethod() {
		return this.currentAction.getMethod();
	}

	public Integer getCurrentLapsTime() {
		return this.currentAction.getCurrentLapsTime();
	}

	public Object getObject() {
		return this.currentAction.getObject();
	}

	public int compareTo(DiscreteActionInterface c) {
		return this.currentAction.compareTo(c);
	}

	public Boolean isEmpty() {
		return !this.hasNext();
	}

	public DiscreteActionInterface next() {
		this.getNextLapsTime();
		Method method = this.getMethod();
		Object object = this.getObject();
		return this;
	}

	public boolean hasNext() {
		return this.currentAction.hasNext();		
	}
	
	public int getNextLapsTime() {
		DiscreteActionInterface nextAction = this.currentAction.next();
		return nextAction.getCurrentLapsTime();
	}

}
