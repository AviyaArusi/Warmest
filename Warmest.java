package task2;

public class Warmest<K, V> {

    //Entry class
        private class Entry<K, V> {
            K key;
            V value;
            Entry<K, V> next;
            Entry<K, V> before;
            Entry<K, V> after;


            Entry(K key, V value) {
                this.key = key;
                this.value = value;
                this.next = null;
                this.before = null;
                this.after = null;
            }

            public K getKey(){
                return this.key;
            }

            @Override
            public String toString() {
                Entry<K,V> temp = this;
                StringBuilder sb = new StringBuilder();
                while (temp != null) {
                    sb.append(temp.key + " -> " + temp.value + ", ");
                    temp = temp.next;
                }
                return sb.toString();
            }

        }
        //End of Entry class.



        //Start of Warmest class:

    private Entry<K, V>[] table;
    private int capacity;
    private double loadFactor;
    private int itemsCount;
    Entry<K, V> last;





    public Warmest() {
        this.capacity = 16;
        this.table = new Entry[capacity];
        this.loadFactor = 0.75;
        this.last = null;
    }


    private void resize() {
        double currentLoad = (double) this.itemsCount / this.capacity;
        //Check how many percentages of the array are full
        if (currentLoad <= this.loadFactor) {
            return;
        }
        int newArraySize = this.capacity * 2;
        this.capacity = newArraySize;
        Entry[] newArray = new Entry[newArraySize];
        // copy items from old to new array
        for (int i = 0; i < this.table.length; i++) {
            if (table[i] != null) {
                int hash = Math.abs(table[i].getKey().hashCode()) % capacity;
                newArray[hash] = table[i];
            }
        }
        this.table = newArray;
    }


    public void put(K key, V value) {
        if (key == null) {
            return;
        }

        //Checking whether the array needs to be increased
        resize();

        // Create a key-value pair
        Entry<K, V> newEntry = new Entry<>(key, value);

        if (last != null) { // Add the new entry to lastList
            last.after = newEntry;
            newEntry.before = last;
            last = newEntry;
        } else {
            last = newEntry;
        }

        // Find the right Bucket by hashing the key
        int hash = Math.abs(key.hashCode()) % capacity;

        // if - Empty Bucket
        if (table[hash] == null) {
            table[hash] = newEntry;
            itemsCount++;


            // else - "Hash Bucket" is not Empty, Known as "Hash Collision"
            // New Entry is created and linked to Previous Node in Same Bucket
        } else {
            Entry<K, V> current = table[hash];
            Entry<K, V> previous = null;
            while (current != null) {
                if (current.key.equals(key)) {
                 lastListReplace(current, newEntry); //Update the lastList
                 return;
                }
                previous = current;
                current = current.next;
            }
            previous.next = newEntry;
        }
    }

    private void lastListReplace(Entry<K, V> oldEntry, Entry<K, V> newEntry){
        if (oldEntry.before != null && oldEntry.after != null){
            oldEntry.before .after = oldEntry.after;
            oldEntry.after.before = oldEntry.before;
            last.before.after = oldEntry;

        }
        else if (oldEntry.before == null & oldEntry.after != null) {
            oldEntry.after.before = oldEntry.before;
        }

            oldEntry.value = newEntry.value;
            oldEntry.before = newEntry.before;
            last = oldEntry;
    }


    public V get(K key) {
        if (key == null) {
            return null;
        }

        // Find the right Bucket by hashing the key
        int hash = Math.abs(key.hashCode()) % capacity;

        // if - "Hash Bucket" is Empty, Return null
        if (table[hash] == null) {
            return null;
            // else - "Hash Bucket" is not Empty
            // Traverse through all the linked Nodes in the Bucket
            // Use `equals` method to find the correct key-value pair
        } else {
            Entry<K, V> current = table[hash];
            while (current != null) {
                if (current.key.equals(key)) {

                    lastListGet(current); //Update the lastList

                    return current.value;
                }
                current = current.next;
            }
        }
        // If the key is not found in the list
        return null;
    }

    private void lastListGet(Entry<K, V> entryToGet){
        if (entryToGet.before != null && entryToGet.after != null){
            entryToGet.before .after = entryToGet.after;
            entryToGet.after.before = entryToGet.before;

        }
        else if (entryToGet.before == null & entryToGet.after != null) {
            entryToGet.after.before = null;

        }

        entryToGet.before = last;
        last.after = entryToGet;
        last = entryToGet;
    }




    public V remove(K key) {
        if (key == null) {
            return null;
        }
        // Find the right Bucket by hashing the key
        int hash = Math.abs(key.hashCode()) % capacity;
//        System.out.println("the hash is: "+hash);

        // if - "Hash Bucket" is Empty, Return null
        if (table[hash] == null) {
            return null;
        }
        // else - "Hash Bucket" is not Empty
        // Traverse through all the linked Nodes in the Bucket
        // Use `equals` method to find the correct key-value pair

        Entry<K, V> current = table[hash];

        if (table[hash].key.equals(key)){ // In case that the head of the list is equals to the key to remove

            lastListRemove(table[hash]);// Remove the entry from lastList

            table[hash] = current.next;

            if (table[hash] == null) { itemsCount--; }

            return current.value;
        }
        // Search the key in the list

        Entry<K, V> previous = current;
        current = current.next;

        while (current != null) {
            if (current.key.equals(key)) {

                lastListRemove(current);// Remove the entry from lastList

                previous.next = current.next;
                return current.value;
            }
            previous = current;
            current = current.next;
        }
        // If the key is not found in the list
        return null;
    }

    private void lastListRemove(Entry<K, V> entryToGet){
        if (entryToGet.before != null && entryToGet.after != null){
            entryToGet.before .after = entryToGet.after;
            entryToGet.after.before = entryToGet.before;

        }
        else if (entryToGet.before == null & entryToGet.after != null) {
            entryToGet.after.before = entryToGet.before;

        }

        last = entryToGet.before;
    }



    public V getLast(){
       if (last == null){ return null;}
        return last.value;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                sb.append(i + " " + table[i] + "\n");
            } else {
                sb.append(i + " " + "null" + "\n");
            }
        }

        return sb.toString();
    }


}