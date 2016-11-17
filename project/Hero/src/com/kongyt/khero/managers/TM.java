package com.kongyt.khero.managers;

import java.util.ArrayList;
import java.util.List;

import com.kongyt.khero.utils.KTimer;

//��ʱ�������� TimerManager
public class TM {

	private static TM instance;
	private List<KTimer> timerList;// Timer����
	private List<KTimer> removeList;// ������Timer����
	private List<KTimer> cancelList;// ����ȡ����Timer����
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

	// ��Ӷ�ʱ��
	public void addTimer(KTimer timer) {
		System.out.println("��Ӷ�ʱ����" + timer.toString());
		timerList.add(timer);
	}

	public void clearAllTimer() {
		System.out.println("ȡ�����ж�ʱ��,��"+timerList.size()+"��!");
		timerList.clear();
	}

	// ȡ����ʱ��
	public void cancelTimer(KTimer timer) {
		System.out.println("��ʱ��ȡ����"+timer.toString());
		if (timer != null)
			cancelList.add(timer);
	}

	// ����������ÿ֡����һ�Σ��贫��֡���ʱ��
	public void step(float deltaTime) {
		// �����ͣ����������һ��
		if (this.isPause)
			return;

		// �Ƴ�����ȡ���Ķ�ʱ��
		timerList.removeAll(cancelList);
		cancelList.clear();

		// ��������δ��ɵĶ�ʱ��
		for (int i = 0; i < timerList.size(); i++) {
			KTimer tmpTimer = timerList.get(i);
			tmpTimer.step(deltaTime);

			// ��ʱ�����������ɾ������
			if (tmpTimer.isCompleted()) {
				removeList.add(tmpTimer);
			}
		}

		// �Ƴ������Ѿ���ɵĶ�ʱ��
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
