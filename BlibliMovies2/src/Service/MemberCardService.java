package Service;

import Model.MemberCard;
import Model.MemberGender;

import java.sql.SQLException;
import java.util.List;

public interface MemberCardService {

    MemberCard getMemberCard(String id, int storeid) throws SQLException;
    List<MemberCard> getAllMemberCard(int storeid) throws SQLException;
    void addMemberCard(MemberCard memberCard) throws SQLException;
    void deleteMemberCard(String id, int storeid) throws SQLException;
    void updateAccout(MemberCard memberCard) throws SQLException;

    MemberGender getMemberGender(String id, int storeid) throws SQLException;
    List<MemberGender> getAllMemberGender(int storeid) throws SQLException;
    void addMemberGender(MemberGender memberGender) throws SQLException;
    void deleteMemberGender(String memberGender, int storeid) throws SQLException;
    void updateMemberGender(MemberGender memberGender) throws SQLException;

}
