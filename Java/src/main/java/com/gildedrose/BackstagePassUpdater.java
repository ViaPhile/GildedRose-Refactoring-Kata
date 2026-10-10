package com.gildedrose;

/**
 * GildedRose-Refactoring-Kata
 *
 * @author vianney On 10/10/2026
 */
public class BackstagePassUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        int dayBeforeConcert = item.sellIn;
        item.sellIn--;

        if (item.sellIn < 0) {
            item.quality = 0;
            return;
        }
        if (dayBeforeConcert <= 5) {
            QualityHelper.increase(item, 3);
        } else if (dayBeforeConcert <= 10) {
            QualityHelper.increase(item, 2);
        } else {
            QualityHelper.increase(item, 1);
        }
    }
}
