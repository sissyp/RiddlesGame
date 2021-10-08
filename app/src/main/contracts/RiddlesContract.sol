pragma solidity ^ 0.8.9;
import "./Ownable.sol";


contract RiddlesContract is Ownable{
    
    struct Riddle {
        
        uint32 id;
        uint32 category;
        
    }
    
    uint32[12] riddlesBeginner;
    uint32[12] riddlesIntermediate;
    uint32[12] riddlesAdvanced;
    
    function createRiddle(uint32 _id, uint32 _category) public onlyOwner {
        if(_category == 1)
            riddlesBeginner[_id] = _id;
        else if (_category == 2)
            riddlesIntermediate[_id] = _id;
        else if (_category == 3)
            riddlesAdvanced[_id] = _id;
    }
    
}