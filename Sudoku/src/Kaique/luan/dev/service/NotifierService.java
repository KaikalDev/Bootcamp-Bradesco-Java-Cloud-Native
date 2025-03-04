package Kaique.luan.dev.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Kaique.luan.dev.service.EventEnum.CLEAR_SPACE;

public class NotifierService {
    private final Map<EventEnum, List<EventLisener>> listeners = new HashMap<>() {{
        put(CLEAR_SPACE, new ArrayList<>());
    }};

    public void subscribe(final EventEnum eventType, final EventLisener listener) {
        var selectedListeners = listeners.get(eventType);
        selectedListeners.add(listener);
    }

    public void notify(final EventEnum eventType) {
        listeners.get(eventType).forEach(l -> l.update(eventType));
    }
}
