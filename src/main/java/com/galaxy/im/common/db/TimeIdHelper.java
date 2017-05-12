package com.galaxy.im.common.db;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TimeIdHelper {
	private static final int TIME_BIT_MOVE = 22;
	private static final int IDC_BIT_MOVE = 18;

	private static final int IDC_MAX = ~(Integer.MAX_VALUE << (TIME_BIT_MOVE - IDC_BIT_MOVE));

	public static final long TIME_BIT = Long.MAX_VALUE << TIME_BIT_MOVE;

	public static final long IDC_ID_BIT = Long.valueOf(IDC_MAX) << IDC_BIT_MOVE;

	public static final long SEQ_BIT = ~(Long.MAX_VALUE << IDC_BIT_MOVE);

	private static final int DEFAULT_IDC = 6;

	private static int DEFAULT_ID_INTEVAL = 5;

	public static boolean isUuidAfterUpdate(long id) {
		return TimeIdHelper.isValidId(id) && id > 3342818919841793l;
	}

	/**
	 * is valid id
	 * 
	 * @param id
	 * @return
	 */
	public static boolean isValidId(long id) {
		return (id > 3000000000000000L) && (id < 4500000000000000L);
	}

	/**
	 * get unix time from id (Accurate to seconds)
	 * 
	 * @param id
	 * @return
	 */
	public static long getTimeFromId(long id) {
		return getTimeNumberFromId(id) + 515483463;
	}

	/**
	 * get time number from id
	 * 
	 * @param id
	 * @return
	 */
	public static long getTimeNumberFromId(long id) {
		return id >> TIME_BIT_MOVE;
	}

	/**
	 * get idc from id
	 * 
	 * @param id
	 * @return
	 */
	public static long getIdcIdFromId(long id) {
		return (id & IDC_ID_BIT) >> IDC_BIT_MOVE;
	}

	/**
	 * get seq from id
	 * 
	 * @param id
	 * @return
	 */
	public static long getSeqFromId(long id) {
		return id & SEQ_BIT;
	}

	/**
	 * get date time from id
	 * 
	 * @param id
	 * @return
	 */
	public static Date getDateFromId(long id) {
		return new Date(getTimeFromId(id) * 1000);
	}

	/**
	 * get id by date
	 * 
	 * @param date
	 * @return
	 */
	public static long getIdByDate(Date date, AtomicLong sid) {
		long uuid = date.getTime() / 1000;

		uuid -= 515483463;
		uuid <<= 22;
		uuid += DEFAULT_IDC << IDC_BIT_MOVE;

		long sidValue = sid.addAndGet(DEFAULT_ID_INTEVAL);
		uuid += sidValue % (1 << IDC_BIT_MOVE);
		return uuid;
	}

	/**
	 * get id by date
	 * 
	 * @param date
	 * @return
	 */
	public static long getIdByDate(Date date, AtomicLong sid, int idc) {
		if (idc < 0 || idc > IDC_MAX) {
			throw new RuntimeException("idc value must in 0 ~ " + IDC_MAX);
		}

		long uuid = date.getTime() / 1000;

		uuid -= 515483463;
		uuid <<= TIME_BIT_MOVE;
		uuid += idc << IDC_BIT_MOVE;

		long sidValue = sid.addAndGet(DEFAULT_ID_INTEVAL);
		uuid += sidValue % (1 << IDC_BIT_MOVE);
		return uuid;
	}

	/**
	 * get id by currentTimeMillis
	 * 
	 * @param date
	 * @return
	 */
	public static long getIdByDate(Long currentTimeMillis, AtomicLong sid, int idc) {
		if (idc < 0 || idc > IDC_MAX) {
			throw new RuntimeException("idc value must in 0 ~ " + IDC_MAX);
		}

		long uuid = currentTimeMillis / 1000;

		uuid -= 515483463;
		uuid <<= TIME_BIT_MOVE;
		uuid += idc << IDC_BIT_MOVE;
		int step = (int) (Math.random() * 10) + 1;
		long sidValue = sid.addAndGet(step);
		// long sidValue = sid.addAndGet(DEFAULT_ID_INTEVAL);
		uuid += sidValue % (1 << IDC_BIT_MOVE);
		return uuid;
	}

}
