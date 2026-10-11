package com.gildedrose;

/**
 * GildedRose-Refactoring-Kata
 *
 * @author vianney On 10/10/2026
 */
public class NormalItemUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        item.sellIn--;
        QualityHelper.decrease(item, item.sellIn < 0 ? 2 : 1);
    }
}
