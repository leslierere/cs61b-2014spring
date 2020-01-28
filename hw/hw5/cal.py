from math import e, log
from scipy.integrate import nquad


lam = log(50) / 100

def poi(t):
    # poisson分布
    return pow(e, -t * lam)

def ex(t):
    # 幂次分布
    return lam * pow(e, -t * lam)
    # return (1-1/lam)^(t-1)*(1/lam)

def f2(y, x):
    # 在x秒中了第一枪，又过了y秒中了第二枪
    return ex(x) * ex(y) * poi(400 - 4 * x - 2 * y)
def f3(z, y, x):
    return ex(x)*ex(y)*ex(z)*poi(400 - 4 * x - 2 * y - z)

def f3_z(x,y):
    return [0, 400-4*x-2*y]

def f3_y(x):
    return [0, 200 - 2 * x]

def f3_x():
    return [0,100]




def f2_x():
    # x的积分范围
    return [0, 100]

def f2_y(x):
    # y的积分范围
    return [0, 200 - 2 * x]



def f1(x):
    return ex(x)* poi(400 - 4 * x)
def f1_x():
    return [0,100]

def f0(x):
    return poi(400)

# p3 = nquad(f3, [f3_z, f3_y, f3_x])
# p2 = nquad(f2, [f2_y, f2_x])
# p1 = nquad(f1, [f1_x])

# p0 = poi(100)#没中枪
# print(p3)
# print(p2) 
# print(p1)

# # print(p2)
# print(p0)
# print("sum")
# print(p0+p1[0]+p2[0]+p3[0])

p = log(50)/100#get
q = 1-p#lose

shot1 = 0
for x in range(1,101):
    shot1 +=(q**(x-1))*p*(q**(200-2*x))
    # print("x:", x)
print(shot1)

shot2 = 0
for x in range(1,101):
    for y in range(1, 200-2*x+1):

        shot2 +=(q**(x-1))*p *(q**(y-1))*p *q**(400-4*x-2*y)
        # print("x:",x," y:",y,"sum:",4*x+2*y+(400-4*x-2*y))
        total = 4*x+2*y+(400-4*x-2*y)
        # if total!= 400:
        #     print("not equal 400, x:",x, "y:", y, "z:",400-4*x-2*y)
print(shot2)

shot3 = 0
for x in range(1,101):
    for y in range(1, 200-2*x+1):
        for z in range(1,400-4*x-2*y+1):
            shot3 += (q**(x-1))*p *(q**(y-1))*p *(q**(z-1))*p *q**(800-8*x-4*y-2*z)
            total = x*4+y*2+z+(800-8*x-4*y-2*z)*0.5
            if total!= 400:
                print("not equal 400, x:",x, "y:", y, "z:",z,"i:",800-8*x-4*y-2*z)
                break
print(shot3)

print("total:", 0.02+shot3+shot2+shot1)