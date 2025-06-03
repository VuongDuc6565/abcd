package hus.oop.statistics;

public abstract class MyAbstractList implements MyList {
    /**
     * Mô tả dữ liệu của list.
     * @return mô tả list theo định dạng [a1 a2 a3 ... an]
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        MyIterator it = iterator(0);
        while (it.hasNext()) {
            builder.append(it.next());
            if (it.hasNext()) builder.append(" ");
        }
        builder.append("]");
        return builder.toString();
    }

}
