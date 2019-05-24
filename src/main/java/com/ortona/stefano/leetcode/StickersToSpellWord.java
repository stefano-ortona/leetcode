package com.ortona.stefano.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StickersToSpellWord {
  int minStickers;

  public int minStickers(String[] stickers, String target) {
    minStickers = target.length() + 1;
    final int[] targetCount = buildCount(target);
    final List<int[]> stickersCount = new ArrayList<>();
    for (final String oneS : stickers) {
      stickersCount.add(buildCount(oneS));
    }
    pickStickerRecursive(targetCount, stickersCount, 0);
    return minStickers == (target.length() + 1) ? -1 : minStickers;
  }

  private int[] buildCount(String word) {
    final int[] count = new int[26];
    for (int i = 0; i < word.length(); i++) {
      count[word.charAt(i) - 'a'] += 1;
    }
    return count;
  }

  private void pickStickerRecursive(int[] target, List<int[]> stickers, int curUsedStickers) {
    if (curUsedStickers >= minStickers) {
      return;
    }
    // for each char, get the list of sticker that matches that char
    final Map<Integer, List<int[]>> char2sticker = new HashMap<Integer, List<int[]>>();
    for (int i = 0; i < target.length; i++) {
      if (target[i] > 0) {
        final List<int[]> matchingS = getMatchingSticker(i, stickers);
        if (matchingS.isEmpty()) {
          return;
        }
        char2sticker.put(i, matchingS);
      }
    }
    if (char2sticker.isEmpty()) {
      // finished, update min
      minStickers = curUsedStickers;
      return;
    }
    // pick the character with least matching stickers
    int minListSize = stickers.size() + 1;
    List<int[]> minList = null;
    for (final List<int[]> oneList : char2sticker.values()) {
      if (oneList.size() < minListSize) {
        minListSize = oneList.size();
        minList = oneList;
      }
    }
    while (!minList.isEmpty()) {
      final int bestIndex = pickBestSticker(target, minList);
      final int[] bestS = minList.remove(bestIndex);
      for (int i = 0; i < target.length; i++) {
        target[i] = target[i] - bestS[i];
      }
      pickStickerRecursive(target, stickers, curUsedStickers + 1);
      // backtrack, try other stickers
      for (int i = 0; i < target.length; i++) {
        target[i] += bestS[i];
      }
    }

  }

  private int pickBestSticker(int[] target, List<int[]> allStickers) {
    // among the matching stickers, pick the one matching most characters
    int bestMatch = 0;
    int pickedStickerIndex = -1;
    for (int index = 0; index < allStickers.size(); index++) {
      int curMatching = 0;
      final int[] oneS = allStickers.get(index);
      for (int i = 0; i < target.length; i++) {
        if (target[i] > 0) {
          curMatching += Math.min(target[i], oneS[i]);
        }
      }
      if (curMatching > bestMatch) {
        pickedStickerIndex = index;
        bestMatch = curMatching;
      }
    }
    return pickedStickerIndex;
  }

  private List<int[]> getMatchingSticker(int charz, List<int[]> sticker) {
    final List<int[]> matchinStickers = new ArrayList<>();
    for (final int[] oneS : sticker) {
      if (oneS[charz] > 0) {
        matchinStickers.add(oneS);
      }
    }
    return matchinStickers;
  }

  public static void main(String[] args) {
    final StickersToSpellWord sW = new StickersToSpellWord();
    final String[] input = new String[] { "control", "heart", "interest", "stream", "sentence", "soil", "wonder",
        "them", "month", "slip", "table", "miss", "boat", "speak", "figure", "no", "perhaps", "twenty", "throw", "rich",
        "capital", "save", "method", "store", "meant", "life", "oil", "string", "song", "food", "am", "who", "fat",
        "if", "put", "path", "come", "grow", "box", "great", "word", "object", "stead", "common", "fresh", "the",
        "operate", "where", "road", "mean" };
    final String target = "stoodcrease";
    System.out.println(sW.minStickers(input, target));
  }

}
