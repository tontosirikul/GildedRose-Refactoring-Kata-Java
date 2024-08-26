package com.gildedrose;

abstract class ItemUpdater {
    protected Item item;

    public ItemUpdater(Item item) {
        this.item = item;
    }

    abstract void update();
}

class AgedBrieUpdater extends ItemUpdater {

    public AgedBrieUpdater(Item item) {
        super(item);
    }

    @Override
    void update() {
        if (item.quality < 50) {
            item.quality += 1;
        }
        item.sellIn -= 1;
    }
    
}

class SulfurasUpdater extends ItemUpdater {

    public SulfurasUpdater(Item item) {
        super(item);
    }

    @Override
    void update() {
    }
    
}

class BackstagePassUpdater extends ItemUpdater {

    public BackstagePassUpdater(Item item) {
        super(item);
    }

    @Override
    void update() {
        if (item.sellIn >= 11) {
            item.quality += 1;
        } else if (item.sellIn <= 10 && item.sellIn > 5) {
            item.quality += 2;
        } else if (item.sellIn <= 5) {
            item.quality += 3;
        }
        if (item.quality > 50) {
            item.quality = 50;
        }
        item.sellIn -= 1;
        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }
    
}

class ConjuredUpdater extends ItemUpdater {

    public ConjuredUpdater(Item item) {
        super(item);
    }

    @Override
    void update() {
        item.sellIn -= 1;
        if (item.sellIn < 0) {
            item.quality -= 4;
        } else {
            item.quality -= 2;
        }
        if (item.quality < 0) {
            item.quality = 0;
        }
    }
    
}

class NormalItemUpdater extends ItemUpdater {
    public NormalItemUpdater(Item item) {
        super(item);
    }

    @Override
    public void update() {
        item.sellIn -= 1;
        if (item.sellIn < 0) {
            item.quality -= 2;
        } else {
            item.quality -= 1;
        }
        if (item.quality < 0) {
            item.quality = 0;
        }
    }
}