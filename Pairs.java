public class Pairs {
    protected String memberA, memberB;
    protected int indexMemberA, indexMemberB;


    /**
     * @param memberA
     * @param memberB
     * @param indexMemberA
     * @param indexMemberB
     */
    public Pairs(String memberA, String memberB, int indexMemberA, int indexMemberB) {
        this.memberA = memberA;
        this.memberB = memberB;
        this.indexMemberA = indexMemberA;
        this.indexMemberB = indexMemberB;
    }

    public String getMemberA() { return memberA; }
    public String getMemberB() { return memberB; }
    public int getAIndex() { return indexMemberA; }
    public int getBIndex() { return indexMemberB; }
}
