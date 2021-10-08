pragma solidity ^ 0.8.9;
import "./SafeMath.sol";

contract PlayerContract {
    
    using SafeMath32 for uint32;
    uint32 id = 0;
    
    struct Avatar {
        string name;
        uint32 avatar_id;
        uint32 level;
        uint32 solved;
    }
    
    Avatar[] public avatars;
    mapping(uint => address) public avatarToOwner; 

    modifier onlyOwnerOf(uint _avatarId) {
        require(msg.sender == avatarToOwner[_avatarId]);
        _;
    }
    
    function _createAvatar(string memory _name) private {
        avatars.push(Avatar(_name, id, 0, 0)) ;
        avatarToOwner[getAvatarId()] = msg.sender;
		setAvatarId();
    }
    
    function createNewAvatar(string memory _name) public {
        _createAvatar(_name);
    }
    
    function levelUp(uint _avatarId, uint _solved) public onlyOwnerOf(_avatarId){
        if(_solved % 3 == 0)
            avatars[_avatarId].level = avatars[_avatarId].level.add(1);
    }
    
    function setAvatarId () public returns(uint32){
		id = id.add(1);
        return id ;
    } 
    
    function getAvatarId() public view returns(uint32){
        return id;
    }
    
   function getAvatarsLength() external view returns(uint){
        return avatars.length;
    }
    
    function getName(uint _avatarId) external view returns(string memory){
        return avatars[_avatarId].name;
    }
    
    function getLevel(uint _avatarId) external view returns(uint32){
        return avatars[_avatarId].level;
    }

}