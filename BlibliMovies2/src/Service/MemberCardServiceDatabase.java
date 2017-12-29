package Service;

import DAO.MemberCardDAO;
import DAO.MemberGenderDAO;
import Model.MemberCard;
import Model.MemberGender;

import java.sql.SQLException;
import java.util.List;

public class MemberCardServiceDatabase implements MemberCardService{
    MemberCardDAO memberCardDAO = new MemberCardDAO();
    MemberGenderDAO memberGenderDAO = new MemberGenderDAO();

    @Override
    public MemberCard getMemberCard(String id, int storeid) throws SQLException {
        return memberCardDAO.getMemberCard(id, storeid);
    }

    @Override
    public MemberCard getMemberCardTrue(String id, int storeid) throws SQLException {
        return memberCardDAO.getMemberCardTrue(id, storeid);
    }

    @Override
    public List<MemberCard> getAllMemberCard(int storeid) throws SQLException {
        return memberCardDAO.getAllMemberCard(storeid);
    }

    @Override
    public List<MemberCard> getAllMemberCardTrue(int storeid) throws SQLException {
        return memberCardDAO.getAllMemberCardTrue(storeid);
    }

    @Override
    public void addMemberCard(MemberCard memberCard) throws SQLException {
        memberCardDAO.addMemberCard(memberCard);
    }

    @Override
    public void deleteMemberCard(String id, int storeid) throws SQLException {
        memberCardDAO.deleteMemberCard(id, storeid);
    }

    @Override
    public void updateAccout(MemberCard memberCard) throws SQLException {
        memberCardDAO.updateMemberCard(memberCard);
    }

    @Override
    public MemberGender getMemberGender(String id, int storeid) throws SQLException {
        return memberGenderDAO.getMemberGender(id, storeid);
    }

    @Override
    public MemberGender getMemberGenderTrue(String id, int storeid) throws SQLException {
        return memberGenderDAO.getMemberGenderTrue(id, storeid);
    }

    @Override
    public List<MemberGender> getAllMemberGender(int storeid) throws SQLException {
        return memberGenderDAO.getAllMemberGender(storeid);
    }

    @Override
    public List<MemberGender> getAllMemberGenderTrue(int storeid) throws SQLException {
        return memberGenderDAO.getAllMemberGenderTrue(storeid);
    }

    @Override
    public void addMemberGender(MemberGender memberGender) throws SQLException {
        memberGenderDAO.addMemberGender(memberGender);
    }

    @Override
    public void deleteMemberGender(String memberGender, int storeid) throws SQLException {
        memberGenderDAO.deleteMemberGender(memberGender, storeid);
    }

    @Override
    public void updateMemberGender(MemberGender memberGender) throws SQLException {
        memberGenderDAO.updateMemberGender(memberGender);
    }
}
