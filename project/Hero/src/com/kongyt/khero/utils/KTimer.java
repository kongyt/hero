package com.kongyt.khero.utils;

import com.kongyt.khero.managers.TM;

public class KTimer {
	// 计时器剩余时间
	private float restTime;

	private boolean isPause;

	// 响应定时器消息的对象
	private KCallback callback;

	// 是否已经触发过了
	private boolean isCompleted;

	private int count;
	private float interval;

	// 触发几次,interval传入间隔时间，count为触发次数，callback为回调函数
	public KTimer(float interval, int count, KCallback callback) {
		if (interval > 0 && callback != null && count > 0) {
			this.restTime = interval;
			this.callback = callback;
			this.isCompleted = false;
			this.count = count;
			this.interval = interval;
		} else {
			this.restTime = -1.0f;
			this.callback = null;
			this.isCompleted = true;
			this.count = 0;
			this.interval = 0;
		}
		isPause = false;
		// 添加timer到定时管理器
		TM.instance().addTimer(this);
	}

	// 取消定时器
	public void cancel() {
		TM.instance().cancelTimer(this);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "触发间隔时间:" + this.interval + "   剩余触发次数:" + count;
	}

	public KTimer(float interval, KCallback callback, boolean loop) {
		if (interval > 0 && callback != null && count > 0) {
			this.restTime = interval;
			this.callback = callback;
			this.isCompleted = false;
			if (loop == true) {
				this.count = Integer.MAX_VALUE;
			} else {
				this.count = 1;
			}
			this.interval = interval;
		} else {
			this.restTime = -1.0f;
			this.callback = null;
			this.isCompleted = true;
			this.count = 0;
			this.interval = 0;
		}
		isPause = false;
		// 添加timer到定时管理器
		TM.instance().addTimer(this);
	}

	// 构造函数，传入等待时间
	public KTimer(float interval, KCallback callback) {
		this(interval, 1, callback);
	}

	// 在render函数中调用
	public void step(float deltaTime) {
		if (isPause == false) {
			this.restTime -= deltaTime;

			if (this.restTime <= 0 && count > 0) {
				System.out.println("定时器触发");

				this.count--;
				this.restTime = this.interval;
				if (count <= 0) {
					this.callback.onCallback(true);
					this.isCompleted = true;
				} else {
					this.callback.onCallback(false);
				}
			}
		}
	}

	// 暂停定时器
	public void pause() {
		if (isPause == false) {
			isPause = true;
		}
	}

	// 恢复定时器
	public void resume() {
		if (isPause == true) {
			isPause = false;
		}
	}

	// 定时器任务是否已经完成
	public boolean isCompleted() {
		return this.isCompleted;
	}

	public void destroy() {
		this.callback = null;
	}
}
