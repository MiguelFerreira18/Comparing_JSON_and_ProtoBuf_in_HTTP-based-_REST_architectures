import protobuf from 'protobufjs';

// Load your .proto file
const root = protobuf.loadSync('../../proto/CreateProductDTO.proto');
// Get your message type
const YourMessage = root.lookupType('proto.CreateProductDTO');

export function encodeMessage(data) {
    const errMsg = YourMessage.verify(data);
    if (errMsg) throw Error(errMsg);

    const message = YourMessage.create(data);

    return YourMessage.encode(message).finish();
}