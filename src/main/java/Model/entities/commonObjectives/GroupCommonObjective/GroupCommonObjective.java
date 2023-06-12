package Model.entities.commonObjectives.GroupCommonObjective;

import Model.entities.commonObjectives.CommonObjective;

/**
 * Abstract class that represent a group in the common objective
 */
abstract public class GroupCommonObjective extends CommonObjective {

    private int groupSize;

    /**
     * Construct a GroupCommonObjective given an ID and groupSize
     *
     * @param ID is the ID of the common objective
     * @param groupSize is the dimension of the group
     */
    public GroupCommonObjective(int ID, int groupSize) {
        super(ID);
        this.groupSize = groupSize;
    }
}