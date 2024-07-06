package org.alexdev.kepler.messages.outgoing.games;

import org.alexdev.kepler.game.games.GameObject;
import org.alexdev.kepler.messages.types.MessageComposer;
import org.alexdev.kepler.server.netty.streams.NettyResponse;

import java.util.List;

public class SNOWSTORM_GAMESTATUS extends MessageComposer {
    private final List<GameObject> currentEvents;
    private int CurrentTurn;
    private int CurrentCheckSum;

    public SNOWSTORM_GAMESTATUS(int CurrentTurn, int CurrentCheckSum, List<GameObject> currentEvents) {
        this.CurrentTurn = CurrentTurn;
        this.CurrentCheckSum = CurrentCheckSum;
        this.currentEvents = currentEvents;
    }

    public void compose(NettyResponse response) {
        response.writeInt(Integer.valueOf(this.CurrentTurn));
        response.writeInt(Integer.valueOf(this.CurrentCheckSum));
        response.writeInt(1); // sub turns
        response.writeInt(this.currentEvents.size());

        for (var obj : this.currentEvents) {
            obj.serialiseObject(response);
        }
    }

    public short getHeader() {
        return 244;
    }
}
