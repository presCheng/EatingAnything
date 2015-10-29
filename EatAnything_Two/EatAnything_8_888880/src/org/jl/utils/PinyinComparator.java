package org.jl.utils;

import java.util.Comparator;

import org.jl.domain.ListInfo;

/**
 * 
 * @author xiaanming
 *
 */
public class PinyinComparator implements Comparator<ListInfo> {

	public int compare(ListInfo o1, ListInfo o2) {
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
