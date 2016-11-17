package com.kongyt.khero.utils;

import com.kongyt.khero.managers.TM;

public class KTimer {
	// ��ʱ��ʣ��ʱ��
	private float restTime;

	private boolean isPause;

	// ��Ӧ��ʱ����Ϣ�Ķ���
	private KCallback callback;

	// �Ƿ��Ѿ���������
	private boolean isCompleted;

	private int count;
	private float interval;

	// ��������,interval������ʱ�䣬countΪ����������callbackΪ�ص�����
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
		// ���timer����ʱ������
		TM.instance().addTimer(this);
	}

	// ȡ����ʱ��
	public void cancel() {
		TM.instance().cancelTimer(this);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "�������ʱ��:" + this.interval + "   ʣ�ഥ������:" + count;
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
		// ���timer����ʱ������
		TM.instance().addTimer(this);
	}

	// ���캯��������ȴ�ʱ��
	public KTimer(float interval, KCallback callback) {
		this(interval, 1, callback);
	}

	// ��render�����е���
	public void step(float deltaTime) {
		if (isPause == false) {
			this.restTime -= deltaTime;

			if (this.restTime <= 0 && count > 0) {
				System.out.println("��ʱ������");

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

	// ��ͣ��ʱ��
	public void pause() {
		if (isPause == false) {
			isPause = true;
		}
	}

	// �ָ���ʱ��
	public void resume() {
		if (isPause == true) {
			isPause = false;
		}
	}

	// ��ʱ�������Ƿ��Ѿ����
	public boolean isCompleted() {
		return this.isCompleted;
	}

	public void destroy() {
		this.callback = null;
	}
}
