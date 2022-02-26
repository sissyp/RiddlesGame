pragma solidity ^ 0.8.9;
import "./SafeMath.sol";
import "./Ownable.sol";

contract PlayerContract is Ownable{
    
    using SafeMath32 for uint32;
    uint32 id = 0;
    uint hintFee = 0.001 ether;
    uint riddleFee = 0.001 ether;
    
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
    
    function levelUp(uint _avatarId, uint _solved) public onlyOwnerOf(_avatarId) {
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

    function play(uint _avatarId) external payable onlyOwnerOf(_avatarId){
        require(msg.value == riddleFee);
        withdraw();
    }
    
    function solve(uint _avatarId) external onlyOwnerOf(_avatarId) {
        avatars[_avatarId].solved = avatars[_avatarId].solved.add(1);
        levelUp(_avatarId, avatars[_avatarId].solved);
        
    }
    
    function giveHint(uint _avatarId) public payable onlyOwnerOf(_avatarId) {
        require(msg.value == hintFee);
        withdraw();
    }
    
    function setHintFee(uint _fee) external onlyOwner {
        hintFee = _fee;
    }
    
    function withdraw() internal onlyOwner {
        address payable _owner = payable(address(uint160(owner())));
        _owner.transfer(address(this).balance);
    }
    
    function reward(address payable _To ) public onlyOwner payable returns (bool) {
        _To.transfer(msg.value);
        return true;
    }

    function getSolved(uint _avatarId) public view returns(uint32){
        return avatars[_avatarId].solved;
    }

}