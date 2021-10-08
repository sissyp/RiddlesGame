pragma solidity ^0.8.9;
import "./Ownable.sol";
import "./PlayerContract.sol";


contract RiddlesHelperContract is PlayerContract,Ownable{
    
    uint hintFee = 0.001 ether;
    
    function solve(uint _avatarId) external onlyOwnerOf(_avatarId){
        Avatar storage myAvatar = avatars[_avatarId];
        myAvatar.solved = myAvatar.solved + 1;
        levelUp(_avatarId, myAvatar.solved);
        
    }
    
    function giveHint(uint _avatarId) public payable {
        require(msg.value == hintFee && msg.sender == avatarToOwner[_avatarId]);
        withdraw();
    }
    
    function setHintFee(uint _fee) external onlyOwner {
        hintFee = _fee;
    }
    
    function withdraw() internal onlyOwner {
        address payable _owner = payable(address(uint160(owner())));
        _owner.transfer(address(this).balance);
  }
  
}
