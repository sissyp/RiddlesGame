package com.aueb.riddlesgame;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class PlayerContract_sol_PlayerContract extends Contract {
    public static final String BINARY = "60806040526000805463ffffffff1916905534801561001d57600080fd5b506108e18061002d6000396000f3fe608060405234801561001057600080fd5b50600436106100935760003560e01c80636b8ff574116100665780636b8ff574146100f5578063834d5fac1461011557806386481d40146101385780638bf948b71461014b578063c8539c4b1461015357600080fd5b80632a08222b14610098578063368929b4146100ae5780635226dc93146100c35780635d9048f0146100d6575b600080fd5b6001546040519081526020015b60405180910390f35b6100c16100bc366004610667565b610194565b005b6100c16100d1366004610718565b6101a0565b60005463ffffffff165b60405163ffffffff90911681526020016100a5565b61010861010336600461073a565b61025f565b6040516100a591906107a0565b61012861012336600461073a565b610315565b6040516100a594939291906107b3565b6100e061014636600461073a565b6103ee565b6100e061042b565b61017c61016136600461073a565b6002602052600090815260409020546001600160a01b031681565b6040516001600160a01b0390911681526020016100a5565b61019d81610463565b50565b60008281526002602052604090205482906001600160a01b031633146101c557600080fd5b6101d06003836107ec565b61025a5761021760018085815481106101eb576101eb61080e565b600091825260209091206001600290920201015463ffffffff6401000000009091048116919061058616565b6001848154811061022a5761022a61080e565b906000526020600020906002020160010160046101000a81548163ffffffff021916908363ffffffff1602179055505b505050565b6060600182815481106102745761027461080e565b9060005260206000209060020201600001805461029090610824565b80601f01602080910402602001604051908101604052809291908181526020018280546102bc90610824565b80156103095780601f106102de57610100808354040283529160200191610309565b820191906000526020600020905b8154815290600101906020018083116102ec57829003601f168201915b50505050509050919050565b6001818154811061032557600080fd5b906000526020600020906002020160009150905080600001805461034890610824565b80601f016020809104026020016040519081016040528092919081815260200182805461037490610824565b80156103c15780601f10610396576101008083540402835291602001916103c1565b820191906000526020600020905b8154815290600101906020018083116103a457829003601f168201915b5050506001909301549192505063ffffffff808216916401000000008104821691600160401b9091041684565b6000600182815481106104035761040361080e565b6000918252602090912060029091020160010154640100000000900463ffffffff1692915050565b600080546104459063ffffffff9081169060019061058616565b6000805463ffffffff191663ffffffff929092169182179055919050565b604080516080810182528281526000805463ffffffff1660208084019190915292820181905260608201819052600180548082018255915281518051929360029092027fb10e2d527612073b26eecdfd717e6a320cf44b4afac2b0732d9fcbe2b7fa0cf601926104d692849201906105b8565b50602082015160019091018054604084015160609094015163ffffffff908116600160401b026bffffffff0000000000000000199582166401000000000267ffffffffffffffff1990931691909416171792909216179055336002600061054260005463ffffffff1690565b63ffffffff16815260200190815260200160002060006101000a8154816001600160a01b0302191690836001600160a01b0316021790555061058261042b565b5050565b600080610593838561085f565b90508363ffffffff168163ffffffff1610156105b1576105b1610895565b9392505050565b8280546105c490610824565b90600052602060002090601f0160209004810192826105e6576000855561062c565b82601f106105ff57805160ff191683800117855561062c565b8280016001018555821561062c579182015b8281111561062c578251825591602001919060010190610611565b5061063892915061063c565b5090565b5b80821115610638576000815560010161063d565b634e487b7160e01b600052604160045260246000fd5b60006020828403121561067957600080fd5b813567ffffffffffffffff8082111561069157600080fd5b818401915084601f8301126106a557600080fd5b8135818111156106b7576106b7610651565b604051601f8201601f19908116603f011681019083821181831017156106df576106df610651565b816040528281528760208487010111156106f857600080fd5b826020860160208301376000928101602001929092525095945050505050565b6000806040838503121561072b57600080fd5b50508035926020909101359150565b60006020828403121561074c57600080fd5b5035919050565b6000815180845260005b818110156107795760208185018101518683018201520161075d565b8181111561078b576000602083870101525b50601f01601f19169290920160200192915050565b6020815260006105b16020830184610753565b6080815260006107c66080830187610753565b63ffffffff95861660208401529385166040830152509216606090920191909152919050565b60008261080957634e487b7160e01b600052601260045260246000fd5b500690565b634e487b7160e01b600052603260045260246000fd5b600181811c9082168061083857607f821691505b6020821081141561085957634e487b7160e01b600052602260045260246000fd5b50919050565b600063ffffffff80831681851680830382111561088c57634e487b7160e01b600052601160045260246000fd5b01949350505050565b634e487b7160e01b600052600160045260246000fdfea2646970667358221220265cf3189a4fe00075589cb8d7093a7a09f4903274e22ba9935ad59a924bd47264736f6c63430008090033";

    public static final String FUNC_AVATARTOOWNER = "avatarToOwner";

    public static final String FUNC_AVATARS = "avatars";

    public static final String FUNC_CREATENEWAVATAR = "createNewAvatar";

    public static final String FUNC_GETAVATARID = "getAvatarId";

    public static final String FUNC_GETAVATARSLENGTH = "getAvatarsLength";

    public static final String FUNC_GETLEVEL = "getLevel";

    public static final String FUNC_GETNAME = "getName";

    public static final String FUNC_LEVELUP = "levelUp";

    public static final String FUNC_SETAVATARID = "setAvatarId";

    @Deprecated
    protected PlayerContract_sol_PlayerContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PlayerContract_sol_PlayerContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PlayerContract_sol_PlayerContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PlayerContract_sol_PlayerContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> avatarToOwner(BigInteger param0) {
        final Function function = new Function(FUNC_AVATARTOOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple4<String, BigInteger, BigInteger, BigInteger>> avatars(BigInteger param0) {
        final Function function = new Function(FUNC_AVATARS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}));
        return new RemoteFunctionCall<Tuple4<String, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple4<String, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<String, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, BigInteger, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> createNewAvatar(String _name) {
        final Function function = new Function(
                FUNC_CREATENEWAVATAR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getAvatarId() {
        final Function function = new Function(FUNC_GETAVATARID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getAvatarsLength() {
        final Function function = new Function(FUNC_GETAVATARSLENGTH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getLevel(BigInteger _avatarId) {
        final Function function = new Function(FUNC_GETLEVEL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_avatarId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getName(BigInteger _avatarId) {
        final Function function = new Function(FUNC_GETNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_avatarId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> levelUp(BigInteger _avatarId, BigInteger _solved) {
        final Function function = new Function(
                FUNC_LEVELUP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_avatarId), 
                new org.web3j.abi.datatypes.generated.Uint256(_solved)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setAvatarId() {
        final Function function = new Function(
                FUNC_SETAVATARID, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static PlayerContract_sol_PlayerContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PlayerContract_sol_PlayerContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PlayerContract_sol_PlayerContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PlayerContract_sol_PlayerContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PlayerContract_sol_PlayerContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PlayerContract_sol_PlayerContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PlayerContract_sol_PlayerContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PlayerContract_sol_PlayerContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PlayerContract_sol_PlayerContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PlayerContract_sol_PlayerContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<PlayerContract_sol_PlayerContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PlayerContract_sol_PlayerContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<PlayerContract_sol_PlayerContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PlayerContract_sol_PlayerContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<PlayerContract_sol_PlayerContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PlayerContract_sol_PlayerContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}