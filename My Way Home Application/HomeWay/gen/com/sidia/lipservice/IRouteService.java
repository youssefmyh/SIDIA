/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /veripark_projects/androidApplication/untitled folder/My Way Home Application/HomeWay/src/com/sidia/lipservice/IRouteService.aidl
 */
package com.sidia.lipservice;
/*
IRouteService mapping between Remote Service and HomeWay client 
*/
public interface IRouteService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.sidia.lipservice.IRouteService
{
private static final java.lang.String DESCRIPTOR = "com.sidia.lipservice.IRouteService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.sidia.lipservice.IRouteService interface,
 * generating a proxy if needed.
 */
public static com.sidia.lipservice.IRouteService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.sidia.lipservice.IRouteService))) {
return ((com.sidia.lipservice.IRouteService)iin);
}
return new com.sidia.lipservice.IRouteService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_loadRout:
{
data.enforceInterface(DESCRIPTOR);
double _arg0;
_arg0 = data.readDouble();
double _arg1;
_arg1 = data.readDouble();
double _arg2;
_arg2 = data.readDouble();
double _arg3;
_arg3 = data.readDouble();
this.loadRout(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.sidia.lipservice.IRouteService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void loadRout(double sourceLat, double sourceLong, double destLat, double destLon) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeDouble(sourceLat);
_data.writeDouble(sourceLong);
_data.writeDouble(destLat);
_data.writeDouble(destLon);
mRemote.transact(Stub.TRANSACTION_loadRout, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_loadRout = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void loadRout(double sourceLat, double sourceLong, double destLat, double destLon) throws android.os.RemoteException;
}
