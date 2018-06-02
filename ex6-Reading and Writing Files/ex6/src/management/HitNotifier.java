package management;

/**
 * interface HitNotifier - is interface that announced when object.
 * is have to add or remover.
 */
public interface HitNotifier {
    /**
     * the function is called whenever Add hl as a listener to hit events..
     *
     * @param hl is the Hit Listener.
     */
    void addHitListener(HitListener hl);

    /**
     * the function is called whenever remove hl as a listener from hit events..
     *
     * @param hl is the Hit Listener.
     */
    void removeHitListener(HitListener hl);
}