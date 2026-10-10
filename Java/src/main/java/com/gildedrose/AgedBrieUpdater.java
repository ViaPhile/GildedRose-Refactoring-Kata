package com.gildedrose;

/**
 * GildedRose-Refactoring-Kata
 *
 * @author vianney On 10/10/2026
 */
public class AgedBrieUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        item.sellIn--;
        QualityHelper.increase(item, item.sellIn < 0 ? 2 : 1);
    }
}
