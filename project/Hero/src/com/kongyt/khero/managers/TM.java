package com.kongyt.khero.managers;

import java.util.ArrayList;
import java.util.List;

import com.kongyt.khero.utils.KTimer;

//定时器管理类 TimerManager
public class TM {

	private static TM instance;
	private List<KTimer> timerList;// Timer链表
	private List<KTimer> removeList;// 待销毁Timer链表
	private List<KTimer> cancelList;// 主动取消的Timer链表
	private boolean isPause;

	private TM() {
		timerList = new ArrayList<KTimer>();
		removeList = new ArrayList<KTimer>();
		cancelList = new ArrayList<KTimer>();
	}

	public static TM instance() {
		if (instance == null)
			instance = new TM();
		return instance;
	}

	public static void relInstance() {
		if (instance != null) {
			instance.destroy();
			instance = null;
		}

	}

	public void pause() {
		this.isPause = true;
	}

	public void resume() {
		this.isPause = false;
	}

	// 添加定时器
	public void addTimer(KTimer timer) {
		System.out.println("添加定时器：" + timer.toString());
		timerList.add(timer);
	}

	public void clearAllTimer() {
		System.out.println("取消所有定时器,共"+timerList.size()+"个!");
		timerList.clear();
	}

	// 取消定时器
	public void cancelTimer(KTimer timer) {
		System.out.println("定时器取消："+timer.toString());
		if (timer != null)
			cancelList.add(timer);
	}

	// 步进函数，每帧调用一次，需传入帧间隔时间
	public void step(float deltaTime) {
		// 如果暂停，则跳过这一步
		if (this.isPause)
			return;

		// 移除所有取消的定时器
		timerList.removeAll(cancelList);
		cancelList.clear();

		// 步进所有未完成的定时器
		for (int i = 0; i < timerList.size(); i++) {
			KTimer tmpTimer = timerList.get(i);
			tmpTimer.step(deltaTime);

			// 定时器完成则加入待删除链表
			if (tmpTimer.isCompleted()) {
				removeList.add(tmpTimer);
			}
		}

		// 移除所有已经完成的定时器
		timerList.removeAll(removeList);
		removeList.clear();
	}

	private void destroy() {
		if (timerList != null) {
			timerList.clear();
			timerList = null;
		}

		if (removeList != null) {
			removeList.clear();
			removeList = null;
		}

		if (cancelList != null) {
			cancelList.clear();
			cancelList = null;
		}
	}

}
