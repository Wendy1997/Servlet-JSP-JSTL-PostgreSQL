package Service;

import DAO.MemberCardDAO;
import DAO.MemberGenderDAO;
import Model.MemberCard;
import Model.MemberGender;

import java.sql.SQLException;
import java.util.List;

public class MemberCardServiceDatabase implements MemberCardService{
    MemberCardDAO memberCardDAO;
    MemberGenderDAO memberGenderDAO;

    @Override
    public MemberCard getMemberCard(String id) throws SQLException {
        memberCardDAO = new MemberCardDAO();
        return memberCardDAO.getMemberCard(id);
    }

    @Override
    public MemberCard getMemberCard(String id, int storeid) throws SQLException {
        memberCardDAO = new MemberCardDAO();
        return memberCardDAO.getMemberCard(id, storeid);
    }

    @Override
    public MemberCard getMemberCardTrue(String id, int storeid) throws SQLException {
        memberCardDAO = new MemberCardDAO();
        return memberCardDAO.getMemberCardTrue(id, storeid);
    }

    @Override
    public List<MemberCard> getAllMemberCard(int storeid, int offset) throws SQLException {
        memberCardDAO = new MemberCardDAO();
        return memberCardDAO.getAllMemberCard(storeid, offset);
    }

    @Override
    public int getCountAllMemberCard(int storeid) throws SQLException {
        memberCardDAO = new MemberCardDAO();
        return memberCardDAO.getCountAllMemberCard(storeid);
    }

    @Override
    public int getIDMemberCardTerbaru(int storeid) throws SQLException {
        memberCardDAO = new MemberCardDAO();
        return memberCardDAO.getIDMemberCardTerbaru(storeid);
    }

    @Override
    public List<MemberCard> getAllMemberCardTrue(int storeid) throws SQLException {
        memberCardDAO = new MemberCardDAO();
        return memberCardDAO.getAllMemberCardTrue(storeid);
    }

    @Override
    public void addMemberCard(MemberCard memberCard) throws SQLException {
        memberCardDAO = new MemberCardDAO();
        memberCardDAO.addMemberCard(memberCard);
    }

    @Override
    public void deleteMemberCard(String id, int storeid) throws SQLException {
        memberCardDAO = new MemberCardDAO();
        memberCardDAO.deleteMemberCard(id, storeid);
    }

    @Override
    public void retrieveMemberCard(String id, int storeid) throws SQLException {
        memberCardDAO = new MemberCardDAO();
        memberCardDAO.retrieveMemberCard(id, storeid);
    }

    @Override
    public void retrieveMemberCard(String id) throws SQLException {
        memberCardDAO = new MemberCardDAO();
        memberCardDAO.retrieveMemberCard(id);
    }

    @Override
    public void updateAccout(MemberCard memberCard) throws SQLException {
        memberCardDAO = new MemberCardDAO();
        memberCardDAO.updateMemberCard(memberCard);
    }

    @Override
    public List<MemberGender> getAllMemberGender(int storeid) throws SQLException {
        memberGenderDAO = new MemberGenderDAO();
        return memberGenderDAO.getAllMemberGender(storeid);
    }

    @Override
    public List<MemberGender> getAllMemberGenderTrue(int storeid) throws SQLException {
        memberGenderDAO = new MemberGenderDAO();
        return memberGenderDAO.getAllMemberGenderTrue(storeid);
    }

    @Override
    public void addMemberGender(MemberGender memberGender) throws SQLException {
        memberGenderDAO = new MemberGenderDAO();
        memberGenderDAO.addMemberGender(memberGender);
    }
}
