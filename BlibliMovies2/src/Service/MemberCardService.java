package Service;

import Model.MemberCard;
import Model.MemberGender;

import java.sql.SQLException;
import java.util.List;

public interface MemberCardService {

    MemberCard getMemberCard(String id, String storename) throws SQLException;
    List<MemberCard> getAllMemberCard(String storename) throws SQLException;
    void addMemberCard(MemberCard memberCard) throws SQLException;
    void deleteMemberCard(String id, String storename) throws SQLException;
    void updateAccout(MemberCard memberCard) throws SQLException;

    MemberGender getMemberGender(String id, String storename) throws SQLException;
    List<MemberGender> getAllMemberGender(String storename) throws SQLException;
    void addMemberGender(MemberGender memberGender) throws SQLException;
    void deleteMemberGender(String memberGender, String storename) throws SQLException;
    void updateMemberGender(MemberGender memberGender) throws SQLException;

}
