package timer;

public class TimeBoundedTimer implements Timer {
	
	private Timer timer2bound;
	private Integer startTime;
	private Integer stopTime;
	
	private Integer next=0;
	private int time=0;
	private boolean hasNext;

	public TimeBoundedTimer(Timer timer2bound, int startTime, int stopTime) throws Exception {
		this.timer2bound = timer2bound;
		if(startTime > stopTime) {
			throw new Exception("StartTime doit être plus petit que StopTime");
		}
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.init();
	}

	public TimeBoundedTimer(Timer timer2bound, int startTime) {
		this.timer2bound = timer2bound;
		this.startTime = startTime;
		this.stopTime = Integer.MAX_VALUE;
		this.init();
	}
	
	private void init() {
		this.next = this.timer2bound.next();
		while (this.next < this.startTime) {
			this.next += this.timer2bound.next();
		}
		if(this.next<this.stopTime) {
			this.hasNext = true;
		}else {
			this.hasNext = false;
		}
	}

	@Override
	public boolean hasNext() {
		return this.hasNext;
	}

	@Override
	public Integer next() {
		Integer nextint = this.next;
		this.time+=this.next;
		if(this.time < this.stopTime) {
			this.next = this.timer2bound.next();
		}else {
			nextint = null;
			this.hasNext=false;
		}
		return nextint;
	}

}
