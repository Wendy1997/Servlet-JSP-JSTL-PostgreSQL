package Service;

import Model.MemberCard;
import Model.MemberGender;

import java.sql.SQLException;
import java.util.List;

public interface MemberCardService {

    MemberCard getMemberCard(String id) throws SQLException;
    MemberCard getMemberCard(String id, int storeid) throws SQLException;
    MemberCard getMemberCardTrue(String id, int storeid) throws SQLException;
    List<MemberCard> getAllMemberCard(int storeid, int offset) throws SQLException;
    int getCountAllMemberCard(int storeid) throws SQLException;
    int getIDMemberCardTerbaru(int storeid) throws SQLException;
    void addMemberCard(MemberCard memberCard) throws SQLException;
    void deleteMemberCard(String id, int storeid) throws SQLException;
    void retrieveMemberCard(String id, int storeid) throws SQLException;
    void retrieveMemberCard(String id) throws SQLException;
    void updateAccout(MemberCard memberCard) throws SQLException;

    List<MemberGender> getAllMemberGender(int storeid) throws SQLException;
    List<MemberGender> getAllMemberGenderTrue(int storeid) throws SQLException;
    void addMemberGender(MemberGender memberGender) throws SQLException;
}
